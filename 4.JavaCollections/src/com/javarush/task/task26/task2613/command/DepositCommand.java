package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] s = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode)
                .addAmount(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        ConsoleHelper.writeMessage(res.getString("success.format"));
    }
}
