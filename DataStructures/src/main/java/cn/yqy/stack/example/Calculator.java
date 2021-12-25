package cn.yqy.stack.example;

import java.util.Arrays;

public class Calculator {

    public static void main(String[] args) {
        String expression = "30+80*2-2";
        Stack2 numStack = new Stack2(expression.length());
        Stack2 operStack = new Stack2(expression.length());
        int num1, num2, res = 0;
        int index = 0;
        int oper;
        char ch;
        String keepNum = "";
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOpertor(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);

                } else {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        operStack.push(ch);
                        numStack.push(res);

                    } else {
                        operStack.push(ch);

                    }
                }

            } else {

                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOpertor((expression.substring(index + 1, index + 2).charAt(0)))) {

                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";

                    }
                }


            }
            index++;
            if (index >= expression.length()) {
                break;
            }

        }
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);

        }
        System.out.println(res);


    }
}

class Stack2 {
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    //判断栈full
    public boolean isFull() {
        return top == maxsize - 1;
    }

    //判断栈null
    public boolean isEmpty() {
        return top == -1;
    }

    //pop栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is null,add data,please");
        }
        int value = getStack()[top];
        top--;
        return value;
    }

    //push栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        getStack()[top] = value;
        System.out.println("stack[" + top + "]" + "=" + value);


    }

    //遍历stack
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(getStack()[i]);
        }
    }

    public Stack2(int maxsize) {
        this.maxsize = maxsize;
        this.stack = new int[maxsize];
    }

    @Override
    public String toString() {
        return "Stack{" +
                "maxsize=" + maxsize +
                ", stack=" + Arrays.toString(stack) +
                '}';
    }

    //为符号分配优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    //判断是否是符号
    public boolean isOpertor(char oper) {
        if (oper == '*' || oper == '/' || oper == '+' || oper == '-') {
            return true;
        }
        return false;
    }

    //返回栈顶 但不是推出
    public int peek() {
        return stack[top];
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;

        }
        return res;
    }

    public int getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(int maxsize) {
        this.maxsize = maxsize;
    }

    public int[] getStack() {
        return stack;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }
}

