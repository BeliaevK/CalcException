package ru.CalcException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Scala on 07.12.2016.
 */
public class Calc {
    public static void main(String[] args) throws IOException, WrongNumFormat, WrongIndOfBound {
       try {
           while (true) {
               try {
                   read();
               }
               catch (WrongNumFormat e){
                   System.out.println("Отловлен неверный формат данных");
               }
               catch (DivisionbyZero e){
                   System.out.println("Деление на ноль невозможно");
               }
               catch (WrongIndOfBound e){
                   System.out.println("Отловлены неверно введенные данные");
               }
           }
       } catch (MainException e){
           System.out.println("Не верно выбран тип калькулятора");
       }
    }

    public static void read () throws IOException, MainException {
        boolean run = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите тип калькулятора: s - обычный, i - инженерный");
        String type = reader.readLine();
        switch (type) {
            case "s":
                System.out.println("Введите ваше выражение в формате 'x + y'");
                String op = reader.readLine();
                Std.std(op);
                break;
            case "i":
                while (run) {
                    try {
                        System.out.println("Инженерный тип калькулятора. Вычисляющий: sin,cos,tan,log,log10." +
                                " \nВведите математическую функцию для вычесления:");
                        String op1 = reader.readLine();
                        if (op1.equals("sin") || op1.equals("cos") || op1.equals("tan") || op1.equals("log") || op1.equals("log10")) {
                            System.out.println("Введите число");
                            int j1 = Integer.parseInt(reader.readLine());
                            Inj.inj(op1, j1);
                            run = false;
                        } else {
                            System.out.println("Неверно введена функция");
                        }
                    } catch (NumberFormatException e) {
                        throw new WrongNumFormat();
                    }
                }
                break;
            default:
            throw new  MainException();
        }
    }

    public static class Std {
        public static void std(String op) throws IOException, DivisionbyZero, WrongNumFormat, WrongIndOfBound {
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
                throw new WrongNumFormat();
            }
            catch (ArithmeticException e) {
                throw new DivisionbyZero();
            }
            catch (IndexOutOfBoundsException e){
                throw new WrongIndOfBound();
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
