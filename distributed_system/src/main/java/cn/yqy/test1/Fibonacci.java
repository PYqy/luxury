package cn.yqy.test1;

import java.util.Stack;

public class Fibonacci {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(1);
        int k = 1;
        while (k < 10) {
            for (int i = 1; i <= 2; i++) {
                Integer f1 = stack.pop();
                int f1Value = f1.intValue();
                Integer f2 = stack.pop();
                int f2Value = f2.intValue();
                stack.push(f1Value);

                stack.push(f1Value + f2Value);
                System.out.println(stack.peek());
                k++;
            }

        }

    }
}
