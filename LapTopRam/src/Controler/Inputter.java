/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Controler.Inputter.inputRange;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    public static YearMonth inputDate() {
        while (true) {
            try {
                System.out.print("Enter YearMonth (MM/yyyy): ");
                String data = sc.nextLine().trim();

                if (data.isEmpty()) {
                    return null;
                }

                YearMonth yearMonth = YearMonth.parse(data, DateTimeFormatter.ofPattern("MM/yyyy"));

                int year = yearMonth.getYear();
                int month = yearMonth.getMonthValue();

                if (year >= 1950 && year <= 2024 && month >= 1 && month <= 12) {
                    return yearMonth;
                } else {
                    System.out.println("Year must be between 0 and 2023, and month must be between 1 and 12. Try again.");
                    return inputDate();
                }
            } catch (java.time.DateTimeException e) {
                System.out.println("Invalid format, try again.");
                return inputDate();
            }
        }
    }

    public static String inputString() {
        try {
            String str = sc.nextLine().trim();
            return str;
        }
        catch(Exception e){
            System.out.println("Invalid String, Try again!!");
            return  inputString();
        }
    }

    public static String inputNonEmptyString() {
        try {
            String str = sc.nextLine();
            if (str.trim().isEmpty()) {
                throw new EmptyStringException();
            }
            return str;
        } catch (EmptyStringException e) {
            System.out.println("String is empty , try again");
            return inputNonEmptyString();
        } catch (Exception e) {
            System.out.println("Invalid String, Try again!!");
            return inputNonEmptyString();
        }
    }

    public static int inputInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, Try again !");
            return inputInt();
        }
    }
    public static int inputIntOrBlank() {
    String input = sc.nextLine().trim();
    if (input.isEmpty()) {
        return 0; 
    }
    try {
        return Integer.parseInt(input);
    } catch (NumberFormatException e) {
        System.out.println("Invalid input, Try again!");
        return inputIntOrBlank();
    }
}


    public static boolean confirm() {
        String text = inputNonEmptyString();
        if (!text.equalsIgnoreCase("yes") && !text.equalsIgnoreCase("no")) {
            System.out.println("Invalid syntax, try again, enter yes/no");
            return confirm();
        } else {
            return text.equalsIgnoreCase("yes");
        }

    }

    public static int inputRange(int min, int max) {

        try {
            int range = inputInt();
            if (range >= min && range <= max) {
                return range;
            } else {
                throw new NumberOutOfRangeException();
            }
        } catch (NumberOutOfRangeException e) {
            System.out.println("Number is out of range, try again");
            return inputRange(min, max);
        }
    }
        public static int inputRangeOrBlank(int min, int max,int oldValue) {
            String input = sc.nextLine().trim();
            if(input.isEmpty()){
                return -1;
            }
        try {
            int range = Integer.parseInt(input);
            if (range >= min && range <= max) {
                return range;
            } else {
                throw new NumberOutOfRangeException();
            }
        } catch (NumberOutOfRangeException e) {
            System.out.println("Number is out of range, try again");
            return inputRange(min, max);
        }
    }

    private static class EmptyStringException extends Exception {

        public EmptyStringException() {
        }
    }

    private static class NumberOutOfRangeException extends Exception {

        public NumberOutOfRangeException() {
        }
    }

}
