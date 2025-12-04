public class Baker2 {
    public static void main(String[] args) {
        System.out.println("Baker 2: Baking bread.");
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        System.out.println("Baker 2: Bread ready!");
    }
}

