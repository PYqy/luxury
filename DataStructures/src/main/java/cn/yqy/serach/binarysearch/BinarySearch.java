package cn.yqy.serach.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 7, 7, 7, 7, 8, 9, 3};
        List<Integer> list = binarySearch(arr, 0, arr.length - 1, 7);
        for (int temp : list) {
            System.out.println(temp);
        }
//        int j = binary(arr, 0, arr.length - 1, 99);
//        System.out.println(i);

//        System.out.println(" "+j);
    }


    public static List<Integer> binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;

        if (arr[mid] > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearch(arr, mid + 1, right, value);
        } else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(mid);
            int temp = mid - 1;
            while (true) {

                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp--;

            }
            temp = mid + 1;
            while (true) {

                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp++;

            }
            return list;
        }

    }

    //    public static int binarySearch(int []arr,int left,int right,int value){
//        if(left>right){
//            return -1;
//        }
//        int mid = (left+right)/2;
//
//        if(arr[mid] > value){
//            return binarySearch(arr,left,mid-1,value);
//        }else if(arr[mid] < value){
//            return binarySearch(arr,mid+1,right,value);
//        }else{
//            return mid;
//        }
//
//    }
    public static int binary(int[] arr, int left, int right, int value) {
        int i = left, j = right;
        while (i < j) {
            int mid = (j + i) / 2;
            if (value == arr[mid]) {
                return mid;
            } else if (value > arr[mid]) {
                i = mid + 1;
            } else if (value < arr[mid]) {
                j = mid - 1;
            }


        }
        return -1;
    }
}
