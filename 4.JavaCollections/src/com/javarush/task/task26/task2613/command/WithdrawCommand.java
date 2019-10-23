package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int value;
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                value = Integer.parseInt(ConsoleHelper.readString());
                if (value <= 0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (!manipulator.isAmountAvailable(value)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());
                result.putAll(manipulator.withdrawAmount(value));
                for (Map.Entry<Integer, Integer> entry : result.entrySet())
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                break;
            } catch (NotEnoughMoneyException | NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
