package cn.yqy.sort;

import java.rmi.server.RMIClassLoader;

public class MergeSort {


    public static void main(String[] args) {
        int[] array = {2, 1, 4, 0, 12, 520, 2, 0, 9, 5, 3, 13, 14};
        print_array(array);
        mergeSort(array, 0, array.length - 1);
        print_array(array);

    }

    private static void print_array(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");

        }
        System.out.println();
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) >> 1;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            _mergeSort(array, left, middle, right);
        }
    }

    private static void _mergeSort(int[] array, int left, int middle, int right) {
        int first = left;
        int second = middle + 1;
        int index = left;
        int[] tmp = new int[array.length];
        while ((first <= middle) && (second <= right)) {
            if (array[first] >= array[second]) {
                tmp[index++] = array[first++];

            } else {
                tmp[index++] = array[first++];
            }
        }
        while (first <= middle) tmp[index++] = array[first++];
        while (second <= right) tmp[index++] = array[second++];

        for (first = left; first <= right; first++) {
            array[first] = tmp[first];
        }
    }
}
