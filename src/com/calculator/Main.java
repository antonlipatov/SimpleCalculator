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
}
