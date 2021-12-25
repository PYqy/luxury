package cn.yqy.recursion;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import sun.tools.tree.GreaterOrEqualExpression;

/**
 * 8*8的期盼要摆放8个皇后，且不能同行同列同对角线，那么每行必定会有一个皇后，
 * 我们可以设一个数组a用来存放每一行皇后的位置，元素值表示第几列（如a[1]=5表示第一行的皇后处于第五个格）
 */

public class Queen8 {

    int max = 8;
    int[] res = new int[max];

    public static void main(String[] args) {
        new Queen8().check(0);


    }

    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            res[n] = i;
            if (judge(n)) {
                check(n + 1);

            }
            //如果冲突，就继续执行 array[n]=i；即将第n个皇后，放置在本行得后移的一个位置
        }

    }


    public boolean judge(int n) {
        /**
         * 说明
         * 1。res[n] == res[i] 表示判断第n个皇后是否与前面的n-1个皇后在同一列
         * 不在同一左对角线：a[n]-a[i]!=n-i
         * 不在同一右对角线：a[n]-a[i]!=-(n-i)
         * 2。Math.abs(n-i) == Math.abs(res[n]-res[i]) 判断第n个皇后是否和第i个皇后在同一斜线
         *
         * n =1 放置第2列 1 n =1 array[1]=1
         * abs(1-0) =1
         *
         *
         */
        for (int i = 0; i < n; i++) {
            if (res[n] == res[i] || Math.abs(n - i) == Math.abs(res[n] - res[i])) {
                return false;
            }
        }
        return true;

    }

    public void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();

    }
}
