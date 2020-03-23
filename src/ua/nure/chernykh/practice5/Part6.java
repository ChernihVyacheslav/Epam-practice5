package ua.nure.chernykh.practice5;

public class Part6 {
    private static final Object mutex = new Object();

    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                try {
                    // state: RUNNABLE
                    while (!isInterrupted()) {
                        // do nothing
                    }

                    // set an interrupted status to false
                    interrupted();

                    synchronized (mutex) {
                        // state: 'WAITING' and 'BLOCKED' after is has been notified
                        mutex.wait(1000);

                        mutex.wait();
                    }
                } catch (InterruptedException ex) {
                    return;
                }
            }
        };

        t.start();

        t.interrupt();

        long start = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while ((currentTime - start) < 500) {
            currentTime = System.currentTimeMillis();
        }
        synchronized (mutex) {
            mutex.notifyAll();

            System.out.println(t.getState());
        }
        start = System.currentTimeMillis();
        while ((currentTime - start) < 500) {
            currentTime = System.currentTimeMillis();
        }
        System.out.println(t.getState());

        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        // state: 'TERMINATED'
        System.out.println(t.getState());
    }

}
