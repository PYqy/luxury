package cn.yqy.binarysearch;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(binarySearch(arr, 0, arr.length - 1, 3));
    }


    public static int binarySearch(int[] arr, int low, int right, int value) {

        int i = low, j = right;
        while (low <= right) {
            int middle = low + (right - low) / 2;
            if (arr[middle] < value) {
                low = middle + 1;
            } else if (arr[middle] == value) {
                return arr[middle];
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }
}
