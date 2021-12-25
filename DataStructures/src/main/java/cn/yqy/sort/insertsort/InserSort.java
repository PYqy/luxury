package cn.yqy.sort.insertsort;

import java.util.Arrays;

/**
 * 插入排序
 * 1、从数组的第二个数据开始往前比较，即一开始用第二个数和他前面的一个比较，如果符合条件，则让他们交换
 * 2、然后再用第三个数和第二个比较，符合则交换，但是此处还得继续往前比较
 * <p>
 * 比如：8，15，20，45；17比45小，需要交换，当时17也比20小也需要交换；当不需要和15交换时就不用和15前面的数据比较了，肯定不要要交换
 * 因为前面的数据是有序的
 * <p>
 * <p>
 * 性能分析：
 * 属于稳定排序；适合于数据量小，部分数据有序的情况
 */

public class InserSort {
    public static void main(String[] args) {
        int[] arr = {4, -1, 333, 22, 11, 93};
        int currVal = 0;
        int currIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            currVal = arr[i];
            currIndex = i - 1;
            while (currIndex >= 0 && currVal < arr[currIndex]) {
                arr[currIndex + 1] = arr[currIndex];
                currIndex--;
            }
            if (currIndex + 1 != i) {
                arr[currIndex + 1] = currVal;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //另一种写法
    public static void insertSort(int[] arr) {
        int index = 0;
        int value = 0;
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    j--;
                } else {
                    break;
                }
            }


        }
        System.out.println(Arrays.toString(arr));
        ;
    }
}
