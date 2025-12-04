public class Baker1 {
    public static void main(String[] args) {
        System.out.println("Baker 1: Mixing dough.");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        System.out.println("Baker 1: Done mixing!");
    }
}

