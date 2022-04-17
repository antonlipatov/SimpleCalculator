package com.calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import  org.junit.jupiter.api.Test;

class MainTest {
    @Test
    @DisplayName("Input '1+ 2' => 3")
    void addTwoArabianNumber_ResultArabianNumber() throws Exception{
        assertEquals("3", Main.calc("1 + 2"));
    }

    @Test
    @DisplayName("Input 'VI / III' => II")
    void divideTwoRomanNumber_ResultRomanNumber() throws Exception{
        assertEquals("II", Main.calc("VI / III"));
    }

    @Test
    @DisplayName("Input '' => code:1, throws Exception: введеная строка не может быть пустой")
    void  emptyString_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("");
        });
        Assertions.assertEquals(1, thrown.getNumber());
    }

    @Test
    @DisplayName("Input '1' => code:2, throws Exception: строка не является математической операцией")
    void  arabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1");
        });
        Assertions.assertEquals(2, thrown.getNumber());
    }

    @Test
    @DisplayName("Input '1 + 2 + 3' => code:3, throws Exception: формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)\n" +
            "\n")
    void  addThreeArabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 + 2 + 3");
        });
        Assertions.assertEquals(3, thrown.getNumber());
    }

    @Test
    @DisplayName("Input '1 +5' => code:4, throws Exception: числа и символ арифмитической операции должен разделять пробел")
    void  incorrectInputStringFormat_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 +5");
        });
        Assertions.assertEquals(4, thrown.getNumber());
    }

    @Test
    @DisplayName("Input 'I + 1' => code:5, throws Exception: используются одновременно разные системы счисления")
    void  addRomanAndArabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("I + 1");
        });
        Assertions.assertEquals(5, thrown.getNumber());
    }

    @Test
    @DisplayName("Input 'str + 1' => code:6, throws Exception: один из операндов не чисело.")
    void  addStringValueAndArabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("str + 1");
        });
        Assertions.assertEquals(6, thrown.getNumber());
    }

    @Test
    @DisplayName("Input '1 + 11' => code:7, throws Exception: одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно.")
    void  addTwoArabianNumberAndOneOfItIsFromIncorrectDiapason_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 + 11");
        });
        Assertions.assertEquals(7, thrown.getNumber());
    }

    @Test
    @DisplayName("Input '5 ^ 8' => code:9, throws Exception: символ арифмитической операции указан неверно")
    void  invalidOperator_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("5 ^ 8");
        });
        Assertions.assertEquals(9, thrown.getNumber());
    }

    @Test
    @DisplayName("Input 'I - II' => code:10, throws Exception: в римской системе нет отрицательных чисел")
    void  subtractTwoRomanNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("I - II");
        });
        Assertions.assertEquals(10, thrown.getNumber());
    }

    @Test
    @DisplayName("Input 'II - II' => code:11, throws Exception: результат вычисления меньше I")
    void  subtractTwoSameRomanNumbers_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("II - II");
        });
        Assertions.assertEquals(11, thrown.getNumber());
    }

    @Test
    @DisplayName("Input 'II + XI' => code:12, throws Exception: одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от I до X  включительно.")
    void  addTwoRomanNumberAndOneOfItIsFromIncorrectDiapason_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("II + XI");
        });
        Assertions.assertEquals(12, thrown.getNumber());
    }

}