package cn.yqy.test;

import cn.yqy.sort.bubblesort.BubbleSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo1 {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};//
        int[] temp = new int[arr.length];
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        //bubble(arr);
        //select(arr);
        //insert(arr);
        //shell(arr);
        //quick(arr,0,arr.length-1);
        //mergeSort(arr,0,arr.length-1,temp);
        radix(arr);
        print(arr);

    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 1) 基数排序(radixsort)属于“分配式排序”(distributionsort)，又称“桶子法”(bucketsort)或binsort，
     *      顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
     * 2) 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
     * 3) 基数排序(RadixSort)是桶排序的扩展
     * 4) 基数排序是1887年赫尔曼·何乐礼发明的。
     * 它是这样实现的:将整数按位数切割成不同的数字，然后按每个位数分别比较。
     * <p>
     * <p>
     * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
     * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
     */

    public static void radix(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketCount = new int[bucket.length];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        int elementLen = (max + "").length();
        System.out.println(elementLen);

        for (int i = 0, n = 1; i < elementLen; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] / n % 10;
                bucket[index][bucketCount[index]] = arr[j];
                bucketCount[index]++;
            }
            //将桶中的数据取出 放在元数组
            int index = 0;
            for (int k = 0; k < bucketCount.length; k++) {
                if (bucketCount[k] != 0) {
                    for (int e = 0; e < bucketCount[k]; e++) {
                        arr[index++] = bucket[k][e];
                    }
                }
                bucketCount[k] = 0;
            }

        }
    }


    /**
     * 归并排序(MERGE-SORT)是利用归并的思想实现的排序方法，
     * 该算法采用经典的分治(divide-and-conquer) 策略(分治法将问题分(divide)成一些小的问题然后递归求解，
     * 而治(conquer)的阶段则将分的阶段得到的各答案"修 补"在一起，即分而治之)。
     */
    //分
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, mid, temp);
        }
    }

    //分治合并的方法
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left;
        int j = mid + 1;
        int top = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[top++] = arr[i++];
            } else {
                temp[top++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[top++] = arr[i++];
        }
        while (j <= right) {
            temp[top++] = arr[j++];
        }

        top = 0;
        int leftTop = left;
        while (leftTop <= right) {
            arr[leftTop++] = temp[top++];
        }


    }

    /**
     * 快速排序(Quicksort)是对冒泡排序的一种改进。
     * 基本思想是:通过一趟排序将要排序的数据分割成独立的两部分，
     * 其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，
     * 整个排序过程可以递归进行，以此达到整个数据变成有序序列
     */

    public static void quick(int arr[], int left, int right) {
        if (left < right) {
            int i = left, j = right;
            int temp;
            int mid = arr[i];
            while (i != j) {
                while (arr[j] >= mid && i < j) {
                    j--;
                }
                while (arr[i] <= mid && i < j) {
                    i++;
                }
                if (i < j) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            arr[left] = arr[i];
            arr[i] = mid;
            quick(arr, left, i - 1);
            quick(arr, i + 1, right);
        }

    }

    /**
     * 希尔排序是希尔(Donald Shell)于 1959 年提出的一种排序算法。
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序;随着增量逐渐减少，每组包含
     * 的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
     */

    public static void shell(int arr[]) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j < arr.length; j += gap) {
                    if (arr[i] < arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 插入排序(Insertion Sorting)的基本思想是:
     * 把 n 个待排序的元素看成为一个有序表和一个无序表，
     * 开始时有序表中只包含一个元素，无序表中包含有 n-1 个元素，
     * 排序过程中每次从无序表中取出第一个元素，
     * 把它的排 序码依次与有序表元素的排序码进行比较，
     * 将它插入到有序表中的适当位置，使之成为新的有序表。
     * <p>
     * 当需要插入的数是较小的数时，后移的次数明显增多，对效率有影响.
     */
    public static void insert(int[] arr) {
        int value, index;
        for (int i = 1; i < arr.length; i++) {
            value = arr[i];
            index = i - 1;
            while (index >= 0 && value < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            if (index + 1 != i) {
                arr[++index] = value;
            }


        }

    }

    /**
     * 选择排序(select sorting)也是一种简单的排序方法。
     * 它的基本思想是:第一次从 arr[0]~arr[n-1]中选取最小值，与 arr[0]交换，
     * 第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，
     * 第三次从 arr[2]~arr[n-1]中选取最小值，
     * 与 arr[2] 交换，...，第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，...,
     * 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值， 与 arr[n-2]交换，总共通过 n-1 次，
     * 得到一个按排序码从小到大排列的有序序列。
     *
     * @param arr
     */
    public static void select(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }

    /**
     * 冒泡排序(Bubble Sorting)的基本思想是:
     * 通过对待排序序列从前向后(从下标较小的元素开始),依次比较 相邻元素的值，
     * 若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
     * <p>
     * <p>
     * 优化: 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，
     * 就说明序列有序，因此要在 排序过程中设置一个标志 flag 判断元素是否进行过交换。
     * 从而减少不必要的比较。(这里说的优化，可以在冒泡排 序写好后，在进行)
     *
     * @param arr
     */
    public static void bubble(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }

    }
}
