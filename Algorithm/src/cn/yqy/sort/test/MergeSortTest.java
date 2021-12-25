package cn.yqy.sort.test;

public class MergeSortTest {
    /**
     * 迭代归并
     * 第一次 比较两个元素 从low 到high 即两个为一组，进行比较 步长为1
     * 第二次 比较四个元素 从low 到 high 即四个为一组，进行比较 步长为2
     * 第三次 比较八个元素 从low 到 high 即八个为一组            步长为4
     * 。。。。
     * 第n次  比较2*n个元素 从 low 到 high ，即2*n个为一组，进行比较 步长为n
     *
     * @param arr
     * @return
     */
    public static int[] iteration_merge_sort(int[] arr) {
        int len = arr.length;
        //存放排序结果数组
        int[] result = new int[len];
        //block 步长 ； start = left
        int block, start;
        //为什么要从1开始？一开始对长度为1的序列进行两两合并
        for (block = 1; block < len * 2; block *= 2) {

            for (start = 0; start < len; start += 2 * block) {
                int low = start;
                int mid = (start + block) < len ? (start + block) : len;
                int high = (start + 2 * block) < len ? (start + 2 * block) : len;
                //两个块的起始下标 及 结束下标
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;
                //开始对两个block 进行归并排序
                while (start1 < end1 && start2 < end2) {
                    result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while (start1 < end1) {
                    result[low++] = arr[start1++];
                }
                while (start2 < end2) {
                    result[low++] = arr[start2++];

                }
            }
            int[] temp = arr;
            arr = result;
            result = temp;
        }
        result = arr;
        return result;
    }


    public static void recursionMargeSort(int a[], int[] result, int left, int right) {
        if (left >= right) {
            return;
        }
        int len = right - left, middle = (len >> 1) + left;
        int start1 = left, end1 = middle;
        int start2 = middle + 1, end2 = right;
        recursionMargeSort(a, result, start1, end1);
        recursionMargeSort(a, result, start2, end2);
        int low = left;
        while (start1 <= end1 && start2 <= end2) {
            result[low++] = a[start1] < a[start2] ? a[start1++] : a[start2++];
        }
        while (start1 <= end1) {
            result[low++] = a[start1++];
        }
        while (start2 <= end2) {
            result[low++] = a[start2++];
        }
        for (low = left; low <= right; low++) {
            a[low] = result[low];
        }

    }

    public static void main(String[] args) {
        int[] a = {2, 55, 3, 22, 0, 4, 5, 3, 222, 1};
        int[] result = new int[a.length];
        recursionMargeSort(a, result, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }


}
