package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;
import static java.lang.System.in;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(in));
    private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String s = bis.readLine();
            if (s.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
            return s;
        } catch (IOException e) {
        }
        return "";
    }

    public static Operation askOperation() throws InterruptOperationException {
        do {
            ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
            ConsoleHelper.writeMessage("1." + res.getString("operation.INFO"));
            ConsoleHelper.writeMessage("2." + res.getString("operation.DEPOSIT"));
            ConsoleHelper.writeMessage("3." + res.getString("operation.WITHDRAW"));
            ConsoleHelper.writeMessage("4." + res.getString("operation.EXIT"));
            int operation = Integer.parseInt(readString());
            return Operation.getAllowableOperationByOrdinal(operation);
        } while (true);
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        if (code.length() != 3) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.denomination.and.count.format"));
        String input = readString();
        if (input.matches("^[1-9]\\d* [1-9]\\d*"))
            return input.split(" ");
        else {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
