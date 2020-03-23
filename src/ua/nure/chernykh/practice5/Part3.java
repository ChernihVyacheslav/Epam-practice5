package ua.nure.chernykh.practice5;

public class Part3 {

    private int k;
    private int t;
    private int counter;
    private int counter2;
    private Thread[] threads;

    public Part3(int n, int k, int t) {
        this.k = k;
        this.t = t;
        threads = new Thread[n];
    }

    public void testSync() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(this::syncAction);
            threads[i].start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void syncAction() {
        for (int i = 0; i < k; i++) {
            synchronized (Part3.class) {
                System.out.printf("%s %s%n", counter, counter2);
                counter++;

                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    return;
                }
                counter2++;
            }
        }
    }

    public void test() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(this::action);
            threads[i].start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void action() {
        for (int i = 0; i < k; i++) {
            System.out.printf("%s %s%n", counter, counter2);
            counter++;
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                return;
            }
            counter2++;
        }
    }

    public void reset() {
        counter = 0;
        counter2 = 0;
    }


    public static void main(String[] args) {
        Part3 p = new Part3(3, 5, 100);
        p.test();
        p.reset();
        p.testSync();
    }
}
