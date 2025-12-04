public class Chef1 {
    public static void main(String[] args) {
        System.out.println("Chef 1: Chopping vegetables.");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println("Chef 1: Done chopping!");
    }
}

