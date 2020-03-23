package ua.nure.chernykh.practice5;

public class Part4 {

//    public static int findMaxWithThreads(String text) {
//        String[] lines = text.split(System.lineSeparator());
//        int[] numbers = new int[lines.length];
//        int max = 0;
//        Thread[] threads = new Thread[lines.length];
//        for(int i = 0; i < lines.length; i++) {
//            String line = lines[i];
//            int index = i;
//            threads[i] = new Thread(() -> {numbers[index] = maxNumberUsingThread(line);});
//            threads[i].start();
//        }
//        for(Thread t : threads) {
//            try{
//                t.join();
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        for(int i = 0; i < numbers.length; i++) {
//            try{
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//            if(numbers[i] > max) {
//                max = numbers[i];
//            }
//        }
//        return max;
//    }
//
//    public static int maxNumberUsingThread(String line) {
//        String[] numbers = line.split(" ");
//        int max = 0;
//        for(int i = 0; i < numbers.length; i++) {
//            try{
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//            if(Integer.parseInt(numbers[i]) > max) {
//                max = Integer.parseInt(numbers[i]);
//            }
//        }
//        return max;
//    }

    public static int findMaxWithThreads2(String text) {
        int[][] numbers = readNumbers(text);
        final int[] max = new int[numbers.length];
        Thread[] threads = new Thread[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            int index = i;
            threads[i] = new Thread(() -> max[index] = getMax(numbers[index]));
            threads[i].start();
        }
        for(Thread t : threads) {
            try{
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return getMax(max);
    }

    public static int getMax(int[] arr) {
        int max = 0;
        for(int x : arr) {
            try{
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if(x > max) {
                max = x;
            }
        }
        return max;
    }


    public static int findMax(String text) {
        int[][] numbers = readNumbers(text);
        int max = 0;
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers[i].length; j++) {
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                if(numbers[i][j] > max) {
                    max = numbers[i][j];
                }
            }
        }
        return max;
    }

    public static int[][] readNumbers(String text) {
        String[] lines = text.split(System.lineSeparator());
        int[][] numbers = new int[lines.length][];
        for(int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            numbers[i] = new int[line.length];
            for(int j = 0; j < line.length; j++) {
                numbers[i][j] = Integer.parseInt(line[j]);
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        String s = Util.readFile("part4.txt");
        long startTime = System.currentTimeMillis();
        int max1 = findMaxWithThreads2(s);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(max1);
        System.out.println(elapsedTime);
        startTime = System.currentTimeMillis();
        int max2 = findMax(s);
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println(max2);
        System.out.println(elapsedTime);
    }
}
