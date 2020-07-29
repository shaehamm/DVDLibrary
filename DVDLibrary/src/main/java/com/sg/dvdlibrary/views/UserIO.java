/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author codedchai
 */
public class UserIO {

    Scanner scn = new Scanner(System.in);

    public void print(String message) {
        System.out.print(message);
    }

    public String readString(String prompt) {
        print(prompt);
        String userInput = scn.nextLine();
        return userInput;
    }

    public String editString(String prompt, String originalValue) {
        String toReturn = readString(prompt);
        if (toReturn.isEmpty()) {
            toReturn = originalValue;
        }
        return toReturn;
    }

    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;

        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);

            try {
                toReturn = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

    public int readInt(String prompt, int incMin, int incMax) {
        int toReturn = 0;

        boolean valid = false;
        while (!valid) {
            toReturn = readInt(prompt);
            valid = toReturn <= incMax && toReturn >= incMin;

        }
        return toReturn;

    }

    public LocalDate readDate(String prompt) {
        LocalDate ld = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String userInput = readString(prompt);
                ld = LocalDate.parse(userInput, DateTimeFormatter
                        .ofPattern("MM/dd/yyyy"));
                validInput = true;
            } catch (DateTimeParseException ex) {

            }
        }
        return ld;
    }

//
//    private boolean isValidDate(String[] parsedInput) {
//        boolean toReturn = false;
//        if (parsedInput[0].length() == 2 && parsedInput[1].length() == 2
//                && parsedInput[2].length() == 4) {
//            try {
//                int month = Integer.parseInt(parsedInput[0]);
//                int day = Integer.parseInt(parsedInput[1]);
//                int year = Integer.parseInt(parsedInput[2]);
//                //im sure theres a better way to check this...
//                boolean validMonth = month > 0 && month < 13;
//                boolean validDay = day > 0 && day < 33;
//                boolean validYear = year > 1900 && year < 2020;
//                if (validMonth && validDay && validYear) {
//                    toReturn = true;
//                }
//            } catch (NumberFormatException ex) {
//
//            }
//        }
//        return toReturn;
//    }

    public LocalDate editDVD(String prompt, LocalDate originalValue) {
        LocalDate toReturn = readDate(prompt);
        if (toReturn.equals(null)) {
            toReturn = originalValue;
        }
        return toReturn;
    }
}
