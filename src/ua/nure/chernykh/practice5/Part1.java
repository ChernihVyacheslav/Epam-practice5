package ua.nure.chernykh.practice5;

public class Part1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }

        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();
        try {
            myRunnableThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }

        Thread myRunnableThread2 = new Thread(Part1::threadMethod);
        myRunnableThread2.start();
        try {
            myRunnableThread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    public static void threadMethod() {
        long start = System.nanoTime();
        while((System.nanoTime() - start) < 1E9) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(333);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            Part1.threadMethod();
        }
    }

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            Part1.threadMethod();
        }
    }

}