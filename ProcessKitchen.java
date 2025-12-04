import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessKitchen {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        List<Thread> outputThreads = new ArrayList<>();
        
        try {
            // Start all chef processes using a loop
            String[] chefClasses = {"Chef1", "Chef2", "Chef3"};
            for (int i = 0; i < chefClasses.length; i++) {
                Process process = new ProcessBuilder("java", chefClasses[i]).start();
                processes.add(process);
                System.out.println("Started " + chefClasses[i] + " process");
                
                // Read output from each process in a separate thread
                Thread outputThread = readProcessOutput(process);
                outputThreads.add(outputThread);
            }
            
            // Wait for all processes to complete
            for (Process process : processes) {
                process.waitFor();
            }
            
            // Wait for all output reading threads to finish
            for (Thread thread : outputThreads) {
                thread.join();
            }
            
            System.out.println("All dishes are ready in the kitchen!");
            
        } catch (IOException e) {
            System.out.println("Error starting processes: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Kitchen was interrupted!");
        }
    }
    
    // Helper method to read and display output from a process
    // Returns the thread so we can wait for it to complete
    private static Thread readProcessOutput(Process process) {
        Thread thread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading process output: " + e.getMessage());
            }
        });
        thread.start();
        return thread;
    }
}

