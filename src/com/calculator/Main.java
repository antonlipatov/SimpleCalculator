package com.calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите два числа от 1 до 10 илиримские цыфры от I до X и арифмитическую операцию.");
        System.out.println("Пример: 2 + 5, III + VI");
        Scanner inputValue = new Scanner(System.in);
        String requestString = inputValue.nextLine();
        inputValue.close();
        String responseString = new String();
        try {
            responseString = calc(requestString);
        } catch (CalculatorException e) {
            responseString = "Ошибка: "+ e.getNumber() +" " + e.getMessage();
        }
        System.out.println(responseString);
    }

    public static String calc(String input) throws CalculatorException {
        int result = 0, a = 0, b = 0;
        String resultString = new String();
        char operator;
        boolean isRomanNumbers = false;
        boolean isNumbers = false;

        //пустая строка
        if(input.length() == 0)
            throw new CalculatorException("введеная строка не может быть пустой", 1);

        //обрезаем пробелы с краев
        input = input.trim();

        //есть ли разделитель пробел
        boolean hasSpaces = input.contains(" ");
        if(!hasSpaces)
            throw new CalculatorException("строка не является математической операцией или числа и символ арифмитической операции должен разделять пробел", 2);

        //Содержит ли строка римские цыфры
        input =  input.toUpperCase();
        if((input.contains("X")) ||(input.contains("V")) || (input.contains("I")))
            isRomanNumbers = true;

        //разделяем на подстроки
        String[] expressionParts = input.split(" ");
        if(expressionParts.length > 3)
            throw new CalculatorException("формат математической операции не удовлетворяет условию - два операнда и один оператор (+, -, /, *)", 3);
        if(expressionParts.length < 3 )
            throw new CalculatorException("с обеих сторон от символа арифмитической операции или отсутствует символ арифмитической операции", 4);
        if( isRomanNumbers && (((expressionParts[0].contains("X") || expressionParts[0].contains("V") || expressionParts[0].contains("I")) && (isInt(expressionParts[2]))) || ((isInt(expressionParts[0])) && (expressionParts[2].contains("X") || expressionParts[2].contains("V") || expressionParts[2].contains("I")))))
            throw new CalculatorException("используются одновременно разные системы счисления", 5);


        //определяем арифмитическую операцию

        operator = expressionParts[1].charAt(0);

        //конвертация чисел
        // если числа арабские
        if(!isRomanNumbers){
            try{
                a = Integer.parseInt(expressionParts[0]);
                b = Integer.parseInt(expressionParts[2]);
                isNumbers = true;
            } catch (Exception ex) {
                isNumbers = false;
            }
            if(!isNumbers)
                throw new CalculatorException("один из операндов не чисело.", 6);
        }

        //если числа римские
        if(isRomanNumbers){
            try {
                a = RomanNumeral.romanToInt(expressionParts[0]);
            } catch (Exception ex) {
                throw new CalculatorException("введеное число больше X", 12);
            }
            try {
                b = RomanNumeral.romanToInt(expressionParts[2]);
            } catch (Exception ex) {
                throw new CalculatorException("введеное число больше X", 12);
            }
            isNumbers = true;
        }

        //проверка условия - оба числа от 1 до 10
        if(isNumbers){
            if((a < 1 || a > 10) || ((b < 1 || b > 10))) {
                if (!isRomanNumbers)
                    throw new CalculatorException("одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно." ,7);
            }
        }

        //вычисление результата выражения
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if(b != 0)
                    result = a / b;
                if(b == 0)
                    throw new CalculatorException("деление на ноль", 8);
                break;
            default:
                throw new CalculatorException("символ арифмитической операции указан неверно", 9);
        }

        //возвращаем результат
        if(!isRomanNumbers)
            resultString = Integer.toString(result);
        if(isRomanNumbers){
            if(result < 0)
                throw new CalculatorException("результат меньше 0, в римской системе нет отрицательных чисел", 10);
            if(result < 1)
                throw new CalculatorException("результат вычисления меньше I", 11);
            resultString = convertNumToRoman(result);
        }

        return resultString;
    }

    public static String convertNumToRoman (int number) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[number];
        return s;
    }

    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
