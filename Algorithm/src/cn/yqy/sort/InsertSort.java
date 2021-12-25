package cn.yqy.sort;

public class InsertSort {
    public static void main(String[] args) {
        int a[] = {4, 1, 5, 33, 0, 1};
        a = insertSort(a, a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static int[] insertSort(int[] a, int len) {
        if (a == null || len == 0) {
            return null;
        }
        for (int i = 1; i < len; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }

            }
            a[j + 1] = temp;
        }
        return a;
    }
}
