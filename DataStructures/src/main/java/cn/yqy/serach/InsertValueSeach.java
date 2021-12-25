package cn.yqy.serach;

/**
 * 8.4.2 插值查找注意事项:
 * 1) 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 * 2) 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InsertValueSeach {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 0 ; i < arr.length ;i ++){
//            arr[i] = i;
//
//        }
        int[] arr = {2, 3, 5, 53, 55, 77, 99, 1000};
        int insertvalue = insertvalue(arr, 0, arr.length - 1, 77);
        System.out.println(insertvalue);

    }

    public static int insertvalue(int arr[], int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal > value) {
            return insertvalue(arr, 0, mid - 1, value);
        } else if (midVal < value) {
            return insertvalue(arr, mid + 1, right, value);
        } else {
            return mid;
        }


    }
}
