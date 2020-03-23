package ua.nure.chernykh.practice5;

public class Demo {


    public static void main(String[] args) {
        System.out.println("Part 1: ");
        Part1.main(args);
        System.out.println("Part 2: ");
        Part2.main(args);
//        System.out.println("Spam: ");
//        Thread t = new Thread(() -> Spam.main(null));
//        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Part 3: ");
        Part3.main(args);
        System.out.println("Part 4: ");
        Util.writeToFile("part4.txt", Util.generateNumbers(4, 100));
        Part4.main(args);
        System.out.println("Part 5: ");
        Part5.main(args);
        System.out.println("Part 6: ");
        Part6.main(args);
    }
}
