package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"info_en");

    @Override
    public void execute() {
        boolean flag = false;
        ConsoleHelper.writeMessage(res.getString("before"));
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                System.out.println(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                flag = true;
            }
        }
        if (!flag) ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
