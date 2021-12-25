package cn.yqy.interviewprogram;

import java.util.Arrays;

public class BigAndSmallBubbleSortP491 {

    public static void main(String[] args) {
        bubbleSort(new int[]{5, -1, 7, 3, 10, 999, 2});
    }

    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[j - 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
