package ru.CalcException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Scala on 07.12.2016.
 */
public class Calc {
    public static void main(String[] args) throws IOException,WrongNumFormat {
        while (true) {
            try {
                read();
            }
            catch (WrongNumFormat e){
                System.out.println("Отловлен неверный формат данных");
            }
        }
    }

    public static void read () throws IOException, WrongNumFormat {
        boolean run = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите тип калькулятора: s - обычный, i - инженерный");
        String type = reader.readLine();
        if (type.equals("s")) {
            System.out.println("Введите ваше выражение в формате 'x + y'");
            String op = reader.readLine();
            Std.std(op);
        }
        else
        if (type.equals("i")){
            while (run) {
                try {
                    System.out.println("Инженерный тип калькулятора. Вычисляющий: sin,cos,tan,log,log10." +
                            " \nВведите математическую функцию для вычесления:");
                    String op = reader.readLine();
                    if (op.equals("sin") || op.equals("cos") || op.equals("tan") || op.equals("log") || op.equals("log10")) {
                        System.out.println("Введите число");
                        int j1 = Integer.parseInt(reader.readLine());
                        Inj.inj(op, j1);
                        run = false;
                    } else {
                        System.out.println("Неверно введена функция");}
                }
                catch (NumberFormatException e) {
                    throw new WrongNumFormat("xZC");
                  //  System.out.println("Неверный формат введенных данных");
                }
            }
        }
        else {
            System.out.println("Выбран неверный тип калькулятора");
        }
    }

    public static class Std {
        public static void std(String op) throws IOException {
            int res = 0;
            String[] str = op.split(" ");
            try {
                int i1 = Integer.parseInt(str[0]);
                String s1 = str[1];
                int i2 = Integer.parseInt(str[2]);
                switch (s1) {
                    case "+":
                        res = i1 + i2;
                        break;
                    case "-":
                        res = i1 - i2;
                        break;
                    case "*":
                        res = i1 * i2;
                        break;
                    case "/":
                        res = i1 / i2;
                        break;
                }
                System.out.println("Результат: " + res);
            }
            catch (NumberFormatException e){
                System.out.println("Неверный формат введенных данных");
            }
            catch (ArithmeticException e) {
                System.out.println("Деление на ноль невозможно");
            }
        }
    }

    public static class Inj {
        public static void inj(String op, int j1) throws IOException {
            double res = 0;
            switch (op) {
                case "sin":
                    res = Math.sin(j1);
                    break;
                case "cos":
                    res = Math.cos(j1);
                    break;
                case "tan":
                    res = Math.tan(j1);
                    break;
                case "log":
                    res = Math.log(j1);
                    break;
                case "log10":
                    res = Math.log10(j1);
                    break;
            }
            System.out.println("Результат: " + res);
        }
    }
}
