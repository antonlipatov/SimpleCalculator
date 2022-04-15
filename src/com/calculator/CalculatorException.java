package com.calculator;

public class CalculatorException extends Exception{
    private int number;
    public int getNumber(){return number;}
    public CalculatorException(String exMessage, int exNumber){
        super(exMessage);
        number = exNumber;
}
