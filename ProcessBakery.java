import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessBakery {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        List<Thread> outputThreads = new ArrayList<>();
        
        try {
            // Start all baker processes using a loop
            String[] bakerClasses = {"Baker1", "Baker2", "Baker3"};
            for (int i = 0; i < bakerClasses.length; i++) {
                Process process = new ProcessBuilder("java", bakerClasses[i]).start();
                processes.add(process);
                System.out.println("Started " + bakerClasses[i] + " process");
                
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
            
            System.out.println("All baked goods are ready in the bakery!");
            
        } catch (IOException e) {
            System.out.println("Error starting processes: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Bakery was interrupted!");
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

