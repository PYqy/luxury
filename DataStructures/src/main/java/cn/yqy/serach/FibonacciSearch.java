package cn.yqy.serach;

import java.util.Arrays;

/**
 * O（logn）
 * (1)数据必须采用顺序存储结构；(2)数据必须有序。
 * 斐波那契数列（黄金分割法）查找算法
 * 黄金分割点是指把一条线段分割成两部分，
 * 是其中一部分与全长之比等于另一部分与这部分之比，
 * 取前三位的近似值是0。618，
 * <p>
 * 与二分和插值相似，仅仅也是改变中间节点的位置，
 * mid不再是中间或插值得到，而是位于黄金分割点附近，
 * 及位于黄金分割点附近，及mid=low+F（k-1）-1
 * （F代表斐波那契数列）
 */

public class FibonacciSearch {
    private static int maxsize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 3));
    }


    //因为后面我们mid=low+F（k-1）-1，需要使用斐波那契数列，
    //因此我们要现货去到一个斐波那契数列
    //非递归方法
    public static int[] fib() {
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length - 1; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写算法

    /**
     * @param a   数组
     * @param key 我们需要查找的关键码
     * @return
     */
    public static int fibSearch(int a[], int key) {
        int low = 0;
        int hight = a.length - 1;
        int k = 0;//表示给波那契数列分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();
        //获取到斐波那契数列分割数值的下标
        while (hight > f[k] - 1) {
            k++;
        }
        //因为f[k]的值可能大雨a的长度，因此我们需要使用Array类，构造一个新数组，并指向temp【】
        //不足的部分会使用0填充
        int temp[] = Arrays.copyOf(a, f[k]);
        //实际需求使用a数组最后的数填充temp
        //举例a[]={1,8,10,89,1000,1234,0,0}=>{1,8,10,89,1000,1234,1234,1234}
        for (int i = hight + 1; i < a.length; i++) {
            a[i] = a[hight];
        }
        //使用while来循环处理找到key
        while (low <= hight) {//只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//我们应该继续像数组前面查找（左）
                hight = mid - 1;

                //为什么是k--

                //1。全部元素 = 前面元素 + 后面元素
                //2。f[k] = f[k-1] +f[k-2]
                //因为前面有f[k-1]个元素，索引可以继续查分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环 mid=f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;

                //为什么是k-=2
                //1。全部元素 = 前面元素 + 后面元素
                //2.f[k] = f[k-1] +f[k-2]
                //3.因为后面我们有f[k-2]所以可以继续拆分 f[k-2] =f[k-3]+f[k-4]
                //4。即在f[k-2]的前面进行查找 k-=2
                //5。下次循环 mid=f[k-1-2]-1
                k -= 2;
            } else {
                if (mid <= hight) {
                    return mid;
                } else {
                    return hight;
                }
            }
        }
        return -1;

    }
}
