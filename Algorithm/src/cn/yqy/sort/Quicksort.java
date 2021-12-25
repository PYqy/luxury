package cn.yqy.sort;
//时间复杂度：O（nlogn）通常比其他O（nlogn）快

/**
 * 步骤：
 * 从数列中挑选一个元素，称为"基准"，重新排序数列，
 * 所有元素比基准值小的摆放在基准值前面，
 * 比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准值就处于数列的中间位置。
 * 这个称为分区操作。
 * 递归的把小于基准值元素的子数列和大雨基准值元素的子数列排序
 */
public class Quicksort {
    private static void quickSort(int[] a, int left, int right) {
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

            //最后将基准值归位
            a[left] = a[i];
            a[i] = temp;
            //继续处理左边
            quickSort(a, left, i - 1);
            // 继续处理右边
            quickSort(a, i + 1, right);

        }
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 8, 3, 9, 10, 22, 19, 1};
        quickSort(a, 0, a.length - 1);

    }


}
