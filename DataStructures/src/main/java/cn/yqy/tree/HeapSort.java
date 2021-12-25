package cn.yqy.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * huile!
 */

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, -1, -999, 6, 8, 5, 9};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(arr);
    }


    public void heapSort(int arr[]) {
        for (int i = arr.length / 2 + 1; i >= 0; i--) {
            heapSort(arr, i, arr.length);
        }

        int temp = 0;
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            heapSort(arr, 0, j);

        }
        System.out.println(Arrays.toString(arr));
    }

    public void heapSort(int arr[], int index, int length) {
        int temp = arr[index];
        for (int k = index * 2 + 1; k < length; k = index * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {

                k++;
            }
            if (arr[index] < arr[k]) {
                arr[index] = arr[k];
                index = k;
            } else {
                break;
            }
        }
        arr[index] = temp;
    }
}
