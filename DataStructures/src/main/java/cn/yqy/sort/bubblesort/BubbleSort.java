package cn.yqy.sort.bubblesort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 如果遇到相等的值 不进行交换，那这种排序方式是稳定的排序方式
 * 原理：比较两个相邻的元素，将值大的元素交换到右边
 * 思路：依次比较相邻的两个数，将比较小的数放在前面，比较大的数放在后面
 * （1）第一次比较：首先比较第一和第二个数，将小数放在前面，将大数放在后面
 * （2）比较第二和第三个数；将小数放在前面，大数放在后面
 * 。。。。
 * （3）如此继续，直到比较到最后的两个数，将小数放在前面，大数放在后面，重复步骤，直到全部排序完成
 * （4）在上面一趟比较完成后，最后一个数一定是数组中最大的一个数，所以在比较第二趟的时候，最后一个数不参与比较
 * （5）在第二趟比较完成后，倒数第二个数也一定是数组中最大的一个数，所以在比较第二趟的时候，最后两个数是不参与比较的
 * （6）依此类推；每一趟比较次数减少依次
 * <p>
 * 平均时间复杂度 O（n2）
 * 稳定排序算法
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);

        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(d));

        sort(arr);
        Date d2 = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf2.format(d2));
//        for (int i = 0 ; i< arr.length;i++){
//            System.out.println(arr[i]);
//        }
    }

    public static void sort(int arr[]) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
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
