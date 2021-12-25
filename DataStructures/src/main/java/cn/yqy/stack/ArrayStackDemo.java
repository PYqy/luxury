package cn.yqy.stack;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        Stack s = new Stack(4);
        boolean flag = true;
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.println("show 展示栈中数据");
            System.out.println("push 添加栈中数据");
            System.out.println("pop  弹出栈中数据");
            System.out.println("exit 退出栈");
            key = sc.next();
            switch (key) {
                case "show":
                    s.show();
                    break;
                case "push":
                    System.out.println("请输入要添加的数据");
                    int value = sc.nextInt();
                    s.push(value);
                    break;
                case "pop":
                    try {
                        int v = s.pop();
                        System.out.println("弹出的数据是：" + v);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    flag = false;
                    break;
                default:
                    System.out.println("请重新输入");

            }
        }

    }
}

class Stack {
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

    public Stack(int maxsize) {
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
