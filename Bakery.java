public class Bakery {
    public static void main(String[] args) {
        Thread baker1 = new Thread(() -> {
            System.out.println("Baker 1: Mixing dough.");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("Baker 1: Done mixing!");
        });
        Thread baker2 = new Thread(() -> {
            System.out.println("Baker 2: Baking bread.");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("Baker 2: Bread ready!");
        });
        Thread baker3 = new Thread(() -> {
            System.out.println("Baker 3: Decorating cake.");
            try { Thread.sleep(700); } catch (InterruptedException e) {}
            System.out.println("Baker 3: Cake decorated!");
        });

        baker1.start();
        baker2.start();
        baker3.start();

        try {
            baker1.join();
            baker2.join();
            baker3.join();
        } catch (InterruptedException e) {
            System.out.println("Bakery was interrupted!");
        }

        System.out.println("All baked goods are ready in the bakery!");
    }
}

