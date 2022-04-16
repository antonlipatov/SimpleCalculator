package com.calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import  org.junit.jupiter.api.Test;

class MainTest {
    @Test
    @DisplayName("Input 1+ 2 => 3")
    void addTwoArabianNumber_ResultArabianNumber() throws Exception{
        assertEquals("3", Main.calc("1 + 2"));
    }

    @Test
    @DisplayName("Input VI / III => II")
    void divideTwoRomanNumber_ResultRomanNumber() throws Exception{
        assertEquals("II", Main.calc("VI / III"));
    }

    @Test
    @DisplayName("Input I - II => throws Exception: в римской системе нет отрицательных чисел")
    void  subtractTwoRomanNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("I - II");
        });
        Assertions.assertEquals("результат меньше 0, в римской системе нет отрицательных чисел", thrown.getMessage());
    }

    @Test
    @DisplayName("Input 1 => throws Exception: строка не является математической операцией")
    void  arabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1");
        });
        Assertions.assertEquals("строка не является математической операцией или числа и символ арифмитической операции должен разделять пробел", thrown.getMessage());
    }

    @Test
    @DisplayName("Input 1 + 2 + 3 => throws Exception: формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)\n" +
            "\n")
    void  addThreeArabianNumber_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 + 2 + 3");
        });
        Assertions.assertEquals("формат математической операции не удовлетворяет условию - два операнда и один оператор (+, -, /, *)", thrown.getMessage());
    }

    @Test
    @DisplayName("Input => throws Exception: введеная строка не может быть пустой")
    void  emptyString_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("");
        });
        Assertions.assertEquals("введеная строка не может быть пустой", thrown.getMessage());
    }

    @Test
    @DisplayName("Input 1 +5 => throws Exception: числа и символ арифмитической операции должен разделять пробел")
    void  incorrectInputStringFormat_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 +5");
        });
        Assertions.assertEquals("с обеих сторон от символа арифмитической операции или отсутствует символ арифмитической операции", thrown.getMessage());
    }

    @Test
    @DisplayName("Input 1 + 11=> throws Exception: одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно.")
    void  addTwoArabianNumberAndOneOfItIsFromIncorrectDiapason_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("1 + 11");
        });
        Assertions.assertEquals("одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно.", thrown.getMessage());
    }

    @Test
    @DisplayName("Input II + XI=> throws Exception: одно из введеных чисел не удовлетваряет условию - Число должно быть в диапазоне от 1 до 10 включительно.")
    void  addTwoRomanNumberAndOneOfItIsFromIncorrectDiapason_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("II + XI");
        });
        Assertions.assertEquals("введеное число больше X", thrown.getMessage());
    }

    @Test
    @DisplayName("Input 5 ^ 8 => throws Exception: символ арифмитической операции указан неверно")
    void  invalidOperator_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("5 ^ 8");
        });
        Assertions.assertEquals("символ арифмитической операции указан неверно", thrown.getMessage());
    }

    @Test
    @DisplayName("Input II - II => throws Exception: результат вычисления меньше I")
    void  subtractTwoSameRomanNumbers_ResultThrowsException() throws Exception{
        CalculatorException thrown = Assertions.assertThrows(CalculatorException.class, () -> {
            Main.calc("II - II");
        });
        Assertions.assertEquals("результат вычисления меньше I", thrown.getMessage());
    }
}