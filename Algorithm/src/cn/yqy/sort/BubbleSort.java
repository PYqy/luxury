package cn.yqy.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int a[] = {3, 1, 5, 2, 44, 1, 0};
        bubbleSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static int[] bubbleSort(int[] a) {
        int j, k, temp;
        int flag = a.length;
        while (flag > 0) {
            k = flag;
            flag = 0;
            for (j = 1; j < k; j++) {
                if (a[j - 1] > a[j]) {
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    flag = j;
                }
            }
        }
        return a;

    }
}
