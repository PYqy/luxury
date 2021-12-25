package cn.it100.interview;

/**
 * 编程题:设有n个人依围成一圈，从第1个人开始报数，数到第m个人出列，
 * 然后从 出列的下一个人开始报数，数到第m个人又出列，...，
 * 如此反复到所有的人全部出列 为止。
 * 设n个人的编号分别为 1，2，...，n，打印出出列的顺序;
 * 要求用 java 实现。
 * <p>
 * 不会
 */
public class exercise110 {
    private static boolean same(int[] p, int l, int n) {
        for (int i = 0; i < l; i++) {
            if (p[i] == n) {
                return true;
            }

        }
        return false;
    }

    public void count(int startNo, int step, int nums) {
        int a[] = new int[nums];
        for (int i = 1; i <= nums; i++) {
            a[i - 1] = i;
        }


    }
}
