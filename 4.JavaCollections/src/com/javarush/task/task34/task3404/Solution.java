package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
    }

    public void recurse(String expression, int countOperation) {
        Pattern brackets = Pattern.compile("\\(([^()]*)\\)");
        Pattern loneNums = Pattern.compile("\\((-?[\\d.]+)\\)");
        Pattern pow = Pattern.compile("(-?[\\d.]+)(\\^)(-?[\\d.]+)");
        Pattern trigonometry = Pattern.compile("()(sin|cos|tan)(-?[\\d.]+)");
        Pattern mul = Pattern.compile("(-?[\\d.]+)([*/])(-?[\\d.]+)");
        Pattern add = Pattern.compile("(-?[\\d.]+)?([+M])(-?[\\d.]+)");
        Pattern doubleMinuses = Pattern.compile("(--)([\\d.]+)");

        if (countOperation == 0)
            expression = expression.replaceAll("-", "M");
        expression = expression.replaceAll(" +", "");
        String calcTemp = expression;
        int start = 0;
        int end = calcTemp.length();
        Matcher matcher = brackets.matcher(expression);
        if (matcher.find()) {
            calcTemp = matcher.group(1);
            start = matcher.start() + 1;
            end = matcher.end() - 1;
        }
        String result = calc(calcTemp, trigonometry);
        String subExpression = expression.length() == end ? "" : expression.substring(end);
        if (!result.equals("")) {
            expression = expression.substring(0, start) + result + subExpression;
            recurse(expression, ++countOperation);
            return;
        }
        result = calc(calcTemp, pow);
        if (!result.equals("")) {
            expression = expression.substring(0, start) + result + subExpression;
            recurse(expression, ++countOperation);
            return;
        }
        result = calc(calcTemp, mul);
        if (!result.equals("")) {
            expression = expression.substring(0, start) + result + subExpression;
            recurse(expression, ++countOperation);
            return;
        }
        result = calc(calcTemp, doubleMinuses);
        if (!result.equals("")) {
            expression = expression.substring(0, start) + result + subExpression;
            recurse(expression, countOperation);
            return;
        }
        result = calc(calcTemp, add);
        if (!result.equals("")) {
            expression = expression.substring(0, start) + result + subExpression;
            recurse(expression, ++countOperation);
            return;
        }
        matcher = loneNums.matcher(expression);
        if (matcher.find()) {
            expression = expression.substring(0, start - 1) + matcher.group(1) + expression.substring(end + 1);
            recurse(expression, countOperation);
            return;
        }
        NumberFormat format = new DecimalFormat("#.##");
        double numericalResult = Double.parseDouble(expression);
        System.out.println(String.format("%s %d", format.format(numericalResult), countOperation).replace(",", "."));
    }

    private String calc(String temp, Pattern pattern) {
        String result = "";
        Matcher matcher = pattern.matcher(temp);
        if (matcher.find())
            result = temp.replaceFirst(pattern.pattern(), numerate(matcher));
        return result;
    }

    private String numerate(Matcher matcher) {
        Map<String, DoubleBinaryOperator> map = new HashMap<>();
        map.put("*", (double a, double b) -> a * b);
        map.put("/", (double a, double b) -> a / b);
        map.put("M", (double a, double b) -> a - b);
        map.put("+", Double::sum);
        map.put("++", (double a, double b) -> b);
        map.put("M-", (double a, double b) -> b);
        map.put("^", Math::pow);
        map.put("cos", (double a, double b) -> Math.cos(Math.toRadians(b)));
        map.put("sin", (double a, double b) -> Math.sin(Math.toRadians(b)));
        map.put("tan", (double a, double b) -> Math.tan(Math.toRadians(b)));
        String left = "0";
        String right = "0";
        try {
            left = matcher.group(1).equals("") ? "0" : matcher.group(1);
        } catch (Exception e) {
        }
        try {
            right = matcher.group(3).equals("") ? "0" : matcher.group(3);
        } catch (Exception e) {
        }
        double parsedLeft = Double.parseDouble(left);
        double parsedRight = Double.parseDouble(right);
        double result = map.get(matcher.group(2)).applyAsDouble(parsedLeft, parsedRight);
        NumberFormat numberFormat = new DecimalFormat("#.##");
        return String.format("%s", numberFormat.format(result)).replace(",", ".");
    }

    public Solution() {
    }
}