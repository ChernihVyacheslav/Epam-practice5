package ua.nure.chernykh.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part5 {
    private static final String FILEPATH = "part5.txt";
    private static final String LINESEPARATOR = System.lineSeparator();
    private static final int TIMES = 20;
    private static final int K = 10;
    private static final Object mutex = new Object();

    public static void writeFromThread(int n, RandomAccessFile file) {
        byte[] b = (n + "").getBytes();
        for (int i = 0; i < TIMES; i++) {
            synchronized (mutex) {
                try {
                    file.seek(i + n * TIMES + LINESEPARATOR.length() * n);
                    file.write(b);
                    safeSleep();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void safeSleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void safeJoin(Thread[] threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Files.delete(Paths.get(FILEPATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(FILEPATH, "rwd")) {
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < TIMES; j++) {
                    randomAccessFile.write(' ');
                }
                if (i != K - 1) {
                    randomAccessFile.write(LINESEPARATOR.getBytes());
                }
            }
            Thread[] threads = new Thread[K];
            for (int i = 0; i < threads.length; i++) {
                int index = i;
                threads[i] = new Thread(() -> writeFromThread(index, randomAccessFile));
                threads[i].start();
            }
            safeJoin(threads);
            byte[] bytes = new byte[(int) randomAccessFile.length()];
            randomAccessFile.seek(0);
            randomAccessFile.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
