package cn.yqy.sort.test;

public class QuickSortTest {
    public static int[] quickSortTest(int[] a, int left, int right) {
        if (left < right) {
            int i, j, t, temp;
            i = left;
            j = right;
            temp = a[left];
            while (i != j) {
                while (a[j] >= temp && i < j) {
                    j--;
                }
                while (a[i] <= temp && i < j) {
                    i++;
                }
                if (i < j) {
                    t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
            a[left] = a[i];
            a[i] = temp;
            quickSortTest(a, left, i - 1);
            quickSortTest(a, i + 1, right);
        }
        return a;

    }


    public static void main(String[] args) {
        int[] a = {4, 3, 2, 1, 6, 8, 30, 1, 22, 1};

        sort(a, 0, a.length - 1);
        for (int i = 0; i <= a.length - 1; i++) {
            System.out.println(a[i]);
        }

    }


    public static void sort(int[] a, int left, int right) {
        if (left < right) {
            int i, j, middle, temp;
            i = left;
            j = right;
            middle = a[left];
            while (i != j) {
                while (middle <= a[j] && i < j) {
                    j--;
                }
                while (middle >= a[i] && i < j) {
                    i++;
                }
                if (i < j) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            a[left] = a[i];
            a[i] = middle;
            sort(a, left, i - 1);
            sort(a, i + 1, right);

        }


    }


}
