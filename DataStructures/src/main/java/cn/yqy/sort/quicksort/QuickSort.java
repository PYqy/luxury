package cn.yqy.sort.quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {4, 1, -1, 3, 2, 5, 0};
        quick(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int left, int right) {
        if (left < right) {
            int i, j, temp, t;
            i = left;
            j = right;
            temp = arr[left];
            while (i != j) {
                while (arr[j] >= temp && i < j) {
                    j--;
                }
                while (arr[i] <= temp && i < j) {
                    i++;
                }
                if (i < j) {
                    t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }

            }
            arr[left] = arr[i];
            arr[i] = temp;
            quick(arr, left, i - 1);
            quick(arr, i + 1, right);
        }

    }
}
