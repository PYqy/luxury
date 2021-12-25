package cn.yqy.stack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //将一个中缀表达式 转换成 后缀表达式
        //1+((2+3)×4)-5
        String s = "1+((2+3)*4)-5";

        List<String> strings = toInfixExpressionList(s);
        System.out.println(strings);
        List<String> list = parserSuffixExcepessionList(strings);
        System.out.println(list);
        int res = calculate(list);
        System.out.println(res);

//        String expression="4 5 * 8 - 60 + 8 2 / +";
//        List<String> list = getListString(strings);
//        int res = calculate(list);
//        System.out.println("最后结果是："+res);


    }

    //将后缀表达式放在list中
    public static List<String> getListString(String suffixExpression) {
        String[] listString = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String temp : listString
                ) {
            list.add(temp);

        }
        return list;
    }

    //将中缀表达式 转换成 后缀表达式
    public static List<String> parserSuffixExcepessionList(List<String> strigns) {
        Stack<String> stack1 = new Stack<String>();
        List<String> list2 = new ArrayList<String>();


        for (String temp : strigns) {
            if (temp.matches("\\d+")) {
                list2.add(temp);
            } else if (temp.equals("(")) {
                stack1.add(temp);
            } else if (temp.equals(")")) {
                while (!stack1.peek().equals("(")) {
                    list2.add(stack1.pop());

                }
                stack1.pop();
            } else {
                while (stack1.size() != 0 && priority(temp) <= priority(stack1.peek())) {
                    list2.add(stack1.pop());
                }
                stack1.push(temp);

            }
        }


        while (stack1.size() != 0) {
            list2.add(stack1.pop());
        }
        return list2;
    }

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //符号优先级
    public static int priority(String operate) {
        int result = 0;
        switch (operate) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("您输入的运算符有误");
        }
        return result;
    }

    //转换成中缀表达式
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        char ch;
        int index;
        int i = 0;
        String str = "";
        while (i < s.length()) {

            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                list.add("" + ch);
                i++;
            } else {
                str = "";
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;
                    i++;
                }
                list.add("" + str);


            }
        }
        return list;

    }

    public static int calculate(List<String> ls) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (String ch : ls) {
            if (ch.matches("\\d+")) {
                stack.push(Integer.parseInt(ch));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                switch (ch) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;

                }
                stack.push(res);


            }

        }
        return stack.pop();

    }

}
