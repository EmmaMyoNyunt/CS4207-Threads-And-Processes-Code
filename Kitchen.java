
public class Kitchen {
    public static void main(String[] args) {
        Thread chef1 = new Thread(() -> {
            System.out.println("Chef 1: Chopping vegetables.");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("Chef 1: Done chopping!");
        });
        Thread chef2 = new Thread(() -> {
            System.out.println("Chef 2: Cooking pasta.");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("Chef 2: Pasta ready!");
        });
        Thread chef3 = new Thread(() -> {
            System.out.println("Chef 3: Plating the dish.");
            try { Thread.sleep(700); } catch (InterruptedException e) {}
            System.out.println("Chef 3: Dish plated!");
        });

        chef1.start();
        chef2.start();
        chef3.start();

        try {
            chef1.join();
            chef2.join();
            chef3.join();
        } catch (InterruptedException e) {
            System.out.println("Kitchen was interrupted!");
        }

        System.out.println("All dishes are ready in the kitchen!");
    }
}
