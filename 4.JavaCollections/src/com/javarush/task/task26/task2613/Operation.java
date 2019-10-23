package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        if (i < 1 || i >= values().length)
            throw new IllegalArgumentException("Invalid argument, try again.");
        return values()[i];
    }
}
