package cn.yqy.interviewprogram;

public class PersonP486 {
    int arr[] = new int[10];

    public static void main(String[] args) {
        //  System.out.println(arr[1]);
        System.out.println(foo(5));
        ;
    }

    public static int foo(int n) {
        if (n < 2) {
            return n;
        }
        return foo(n - 1) + foo(n - 2);
    }
}
