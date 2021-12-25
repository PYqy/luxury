package cn.yqy.sort.merge;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];
        margeSort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 分+和 的方法
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */

    public static void margeSort(int arr[], int left, int right, int temp[]) {
        if (left < right) {
            int middle = (left + right) / 2;

            margeSort(arr, left, middle, temp);
            margeSort(arr, middle + 1, right, temp);
            marge(arr, left, middle, right, temp);
        }
        System.out.println(Arrays.toString(temp));
    }

    /**
     * 合并的方法
     *
     * @param arr
     * @param left
     * @param middle
     * @param right
     * @param temp
     */

    public static void marge(int arr[], int left, int middle, int right, int[] temp) {
        int top = 0, i = left, j = middle + 1;


        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[top] = arr[i];
                top++;
                i++;
            } else {
                temp[top] = arr[j];
                top++;
                j++;

            }
        }
        while (i <= middle) {
            temp[top] = arr[i];
            i++;
            top++;
        }
        while (j <= right) {
            temp[top] = arr[j];
            j++;
            top++;
        }

        top = 0;
        int templeft = left;

        while (templeft <= right) {
            arr[templeft] = temp[top];
            top++;
            templeft++;

        }
    }
}
