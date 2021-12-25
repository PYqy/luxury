package cn.yqy.sort.radixsort;

import java.security.Key;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1。二维数组包含10个一维数组
        //2。为了防止在放入数的时候，数据溢出，则每个一维数组 ，大小定位 arr。len
        //3。明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        int[] num = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            //针对每个元素的对应位进行排序处理
            for (int j = 0; j < arr.length; j++) {

                int value = arr[j] / n % 10;
                bucket[value][num[value]] = arr[j];
                num[value]++;

            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放在原来数组）
            int index = 0;
            //遍历每一桶，并将桶中数据放在原数组
            for (int i2 = 0; i2 < num.length; i2++) {
                if (num[i2] != 0) {
                    for (int j = 0; j < num[i2]; j++) {
                        arr[index++] = bucket[i2][j];

                    }
                }
                num[i2] = 0;

                //第i+1轮处理后，需要将每个num[i]=0

            }
        }
        System.out.println("基数排序后 " + Arrays.toString(arr));

    }
}
