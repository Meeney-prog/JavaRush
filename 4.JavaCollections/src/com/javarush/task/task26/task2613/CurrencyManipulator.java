package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denomination > 0 && count > 0)
            if (this.denominations.containsKey(denomination))
                this.denominations.put(denomination, this.denominations.get(denomination) + count);
            else this.denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        if (!denominations.isEmpty())
            return denominations.entrySet().stream()
                    .mapToInt(entry -> entry.getKey() * entry.getValue())
                    .sum();
        else return 0;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        TreeMap<Integer, Integer> atm = new TreeMap<>(denominations);
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : atm.descendingMap().entrySet()) {
            int cur = entry.getKey();
            int number = entry.getValue();
            while (number > 0) {
                if (sum + cur > expectedAmount)
                    break;
                sum += cur;
                atm.put(cur, --number);
                if (result.containsKey(cur)) {
                    int n = result.get(cur) + 1;
                    result.put(cur, n);
                } else result.put(cur, 1);
            }
            if (sum == expectedAmount) break;
        }
        if (sum != expectedAmount) {
            sum = 0;
            result = new HashMap<>();
            atm = new TreeMap<>(denominations);
            for (Map.Entry<Integer, Integer> entry : atm.descendingMap().entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (expectedAmount % key == 0) {
                    int number = expectedAmount / key;
                    if (number <= value) {
                        sum = key * number;
                        result.put(key, number);
                        atm.put(key, value - number);
                        break;
                    }
                }
            }
            if (sum == 0)
                throw new NotEnoughMoneyException();
        }
        atm.entrySet().removeIf(integerIntegerEntry -> integerIntegerEntry.getValue() == 0);
        denominations = new HashMap<>(atm);
        if (sum != expectedAmount)
            throw new NotEnoughMoneyException();
        return result;
    }
}
