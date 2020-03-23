package ua.nure.chernykh.practice5;

import java.io.InputStream;

public class Part2 {
    private static final InputStream STD_IN = System.in;
    private static final char NEWLINE = 10;

    public static void main(String[] args) {
        System.setIn(new MyInputStream());
        Thread t = new Thread(() -> Spam.main(null));
        t.start();
//        try {
//            System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes(ENCODING)));
//        } catch (UnsupportedEncodingException ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.setIn(STD_IN);
    }

    private static final class MyInputStream extends InputStream {

        private boolean waited;

        @Override
        public int read() {
            if(!waited) {
                long start = System.currentTimeMillis();
                long currentTime = System.currentTimeMillis();
                while ((currentTime - start) < 1900) {
                    currentTime = System.currentTimeMillis();
                }
                waited = true;
                return NEWLINE;
            }
            return -1;
        }
    }
}
