package ua.nure.chernykh.practice5;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;

    public Spam(String[] messages, int[] times) {
        if(messages.length == times.length) {
            threads = new Thread[messages.length];
            for(int i = 0; i < messages.length; i++) {
                threads[i] = new Worker(messages[i], times[i]);
            }
        }
    }

    public Thread[] getThreads() {
        return threads.clone();
    }

    public void start() {
        for (Thread t: threads) {
            t.start();
        }
    }

    public void stop() {
        for (Thread t: threads) {
            t.interrupt();
        }
    }

    private static class Worker extends Thread {

        private String message;
        private int time;

        public Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        spam.start();
        Scanner s = new Scanner(System.in);
        System.out.println(s.nextLine());
        spam.stop();
        for(Thread t : spam.getThreads()) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
