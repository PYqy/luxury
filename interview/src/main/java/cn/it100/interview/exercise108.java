package cn.it100.interview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 用java实现一种排序，
 * java类实现序列化的两种方法，
 * 实现java.io.Serializable接口
 * 如在collection框架中，实现比较要实现什么样的接口
 * Collection框架中实现比较 要实现Comparable 接口和 Comparator接口
 */

public class exercise108 {
    public static void main(String[] args) {
        int arr[] = {5, 2, 6, 3, 6};
        new insertSort().insert(arr, 3);

    }

}

class insertSort {
    public void insert(int arr[], int value) {

        int temp;
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int currvalue = arr[i];
            for (; j >= 0; --j) {
                if (arr[j] > currvalue) {
                    arr[j + 1] = arr[j];
                } else {

                    break;
                }

                arr[j] = currvalue;

            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
