package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static final Map<String, Integer> romanNumbers = new HashMap<>();

    static {
        romanNumbers.put("I", 1);
        romanNumbers.put("II", 2);
        romanNumbers.put("III", 3);
        romanNumbers.put("IV", 4);
        romanNumbers.put("V", 5);
        romanNumbers.put("VI", 6);
        romanNumbers.put("VII", 7);
        romanNumbers.put("VIII", 8);
        romanNumbers.put("IX", 9);
        romanNumbers.put("X", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split("\\s+");
        if (parts.length != 3) {
            throw new Exception("Неправильный формат выражения");
        }

        String firstOperand = parts[0];
        String operator = parts[1];
        String secondOperand = parts[2];

        boolean isRoman = isRomanNumeral(firstOperand) && isRomanNumeral(secondOperand);

        int result;
        switch (operator) {
            case "+":
                result = toArabic(firstOperand, isRoman) + toArabic(secondOperand, isRoman);
                break;
            case "-":
                result = toArabic(firstOperand, isRoman) - toArabic(secondOperand, isRoman);
                break;
            case "*":
                result = toArabic(firstOperand, isRoman) * toArabic(secondOperand, isRoman);
                break;
            case "/":
                result = toArabic(firstOperand, isRoman) / toArabic(secondOperand, isRoman);
                break;
            default:
                throw new Exception("Неподдерживаемая операция");
        }

        return isRoman ? toRoman(result) : String.valueOf(result);
    }

    private static int toArabic(String numeral, boolean isRoman) throws Exception {
        if (isRoman) {
            if (!romanNumbers.containsKey(numeral)) {
                throw new Exception("Неправильный формат числа");
            }
            return romanNumbers.get(numeral);
        } else {
            try {
                int value = Integer.parseInt(numeral);
                if (value < 1 || value > 10) {
                    throw new Exception("Число должно быть от 1 до 10");
                }
                return value;
            } catch (NumberFormatException e) {
                throw new Exception("Неправильный формат числа");
            }
        }
    }

    private static boolean isRomanNumeral(String numeral) {
        return numeral.matches("^(I | II | III | IV | V | VI | VII | VIII | IX | X)$");
    }

    private static String toRoman(int number) throws Exception {
        if (number < 1) {
            throw new Exception("Результат не может быть меньше 1");
        }
        StringBuilder roman = new StringBuilder();
        for (Map.Entry<String, Integer> entry : romanNumbers.entrySet()) {
            while (number >= entry.getValue()) {
                roman.append(entry.getKey());
                number -= entry.getValue();
            }
        }
        return roman.toString();
    }
}