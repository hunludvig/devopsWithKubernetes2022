package fi.hunludvig.forklift;

import java.util.concurrent.CountDownLatch;

public class Forklift {

    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        var numberOfThreads = Integer.parseInt(System.getenv("THREADS"));
        for(var i = 0; i < numberOfThreads; i++) {
            final int index = i;
            System.out.println("Thread " + index + " created");
            new Thread(() -> {
                System.out.println("Thread " + index + " started");
                try{
                    latch.await();
                } catch(InterruptedException ignore) {}
                System.out.println("Thread " + index + " finished");
            }).start();
        }
        System.out.println("All threads created");
        latch.countDown();
        System.out.println("Main thread finished");
    }
}
