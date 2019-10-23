package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
