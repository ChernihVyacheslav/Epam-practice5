package ua.nure.chernykh.practice5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Util {
    private static final String ENCODING = "Cp1251";

    private Util(){

    }

    public static String readFile(String filepath) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filepath), ENCODING))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static boolean writeToFile(String filePath, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), ENCODING))) {
            bufferedWriter.write(text);
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static String generateNumbers(int m, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(getRandomIntegerBetweenRange(0, 10000)).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private static int getRandomIntegerBetweenRange(double min, double max) {
        try {
            double x = (SecureRandom.getInstance("SHA1PRNG").nextDouble() * ((max - min) + 1)) + min;
            return (int) x;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return 0;
    }
}