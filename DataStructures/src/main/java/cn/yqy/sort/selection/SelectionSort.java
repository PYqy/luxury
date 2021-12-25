package cn.yqy.sort.selection;

/**
 * 选择排序
 * 从头至尾扫描序列，找出最小的一个元素，和第一个元素交换，接着从剩下的元素中继续着这选择和交换方式
 */

public class SelectionSort {
    public static void main(String[] args) {
        int arr[] = {5, -1, 2, 5, 2, 33};
        selectionSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIdex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIdex = j;
                }
            }
            if (minIdex != i) {
                arr[minIdex] = arr[i];
                arr[i] = min;
            }
            System.out.println(arr.toString());
        }
    }
}
