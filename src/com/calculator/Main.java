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
            throw new CalculatorException("числа и символ арифмитической операции должен разделять пробел", 2);

        //Содержит ли строка римские цыфры
        input =  input.toUpperCase();
        if((input.contains("X")) ||(input.contains("V")) || (input.contains("I")))
            isRomanNumbers = true;

        //разделяем на подстроки
        String[] expressionParts = input.split(" ");
        if(expressionParts.length != 3 )
            throw new CalculatorException("числа и символ арифмитической операции должен разделять пробел с обеих сторон от символа арифмитической операции или отсутствует символ арифмитической операции ", 3);


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
                throw new CalculatorException("строка не содержит чисел.", 4);
        }

        //если числа римские
        if(isRomanNumbers){
            a = RomanNumeral.romanToInt(expressionParts[0]);
            b = RomanNumeral.romanToInt(expressionParts[2]);
            isNumbers = true;
        }

        //проверка условия - оба числа от 1 до 10
        if(isNumbers){
            if((a < 1 || a > 10) || ((b < 1 || b > 10))) {
                if (!isRomanNumbers)
                    throw new CalculatorException("одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно." ,5);
                if(isRomanNumbers)
                    throw new CalculatorException("одно из введеных чисел не удовлетваряет условию - Число должно быть римским в диапазоне от I до X включительно.", 6);
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
                    throw new CalculatorException("деление на ноль", 7);
                break;
            default:
                throw new CalculatorException("символ арифмитической операции указан неверно", 8);
        }

        //возвращаем результат
        if(!isRomanNumbers)
            resultString = Integer.toString(result);
        if(isRomanNumbers){
            if(result < 1)
                throw new CalculatorException("результат вычисления меньше I", 9);
            resultString = convertNumToRoman(result);
        }

        return resultString;
    }
}
