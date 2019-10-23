package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"login_en");
    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            try {
                ConsoleHelper.writeMessage(res.getString("specify.data"));
                long num = Long.parseLong(ConsoleHelper.readString());
                int pin = Integer.parseInt(ConsoleHelper.readString());
                if (String.valueOf(num).length() < 12 || String.valueOf(pin).length() < 4 || num < 0 || pin < 0) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
                if (validCreditCards.containsKey(String.valueOf(num))
                        && validCreditCards.getString(String.valueOf(num)).equals(String.valueOf(pin))){
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                } else ConsoleHelper.writeMessage(res.getString("not.verified.format"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
