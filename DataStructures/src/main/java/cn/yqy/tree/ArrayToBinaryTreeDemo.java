package cn.yqy.tree;

/**
 * 需求: 给你一个数组 {1,2,3,4,5,6,7}，要求以二叉树前序遍历的方式进行遍历。 前序遍历的结果应当为 1,2,4,5,3,6,7
 */
public class ArrayToBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayToBinaryTreeDemo d = new ArrayToBinaryTreeDemo();
        // d.arrayToTree(arr,0);
        //d.arrayToLastTree(arr,0);
        d.arrayToMiddleLastTree(arr, 0);
    }

    public void arrayToTree(int[] arr, int index) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组错误");
            return;
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            arrayToTree(arr, index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            arrayToTree(arr, index * 2 + 2);
        }


    }

    public void arrayToLastTree(int[] arr, int index) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组错误");
            return;
        }

        if ((index * 2 + 1) < arr.length) {
            arrayToLastTree(arr, index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            arrayToLastTree(arr, index * 2 + 2);
        }
        System.out.println(arr[index]);

    }

    public void arrayToMiddleLastTree(int[] arr, int index) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组错误");
            return;
        }

        if ((index * 2 + 1) < arr.length) {
            arrayToMiddleLastTree(arr, index * 2 + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            arrayToMiddleLastTree(arr, index * 2 + 2);
        }


    }
}
