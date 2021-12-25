package cn.yqy.sort.shellsort;

import java.util.Arrays;

/**
 * 希尔排序：也称缩小增量排序
 * 插入法 sort
 * 位移法 sort2
 */
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        sort(arr);

    }

    public static void sort(int[] arr) {
        int temp = 0;
        for (int num = arr.length / 2; num > 0; num /= 2) {
            for (int i = num; i < arr.length; i++) {
                for (int j = i - num; j >= 0; j -= num) {
                    if (arr[j] > arr[j + num]) {
                        temp = arr[j];
                        arr[j] = arr[j + num];
                        arr[j + num] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void sort2(int[] arr) {
        int temp = 0;
        int j = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {

                    while (temp < arr[j - gap] && j - gap >= 0) {
                        arr[j] = arr[j - gap];

                        j -= gap;
                    }
                    arr[j] = temp;
                }

            }
        }
    }


}
