package meituan;

import java.util.Scanner;

/**
 * 小团的蛋糕铺长期霸占着美团APP中“蛋糕奶茶”栏目的首位，因此总会吸引各路食客前来探店。
 * <p>
 * 小团一天最多可以烤n个蛋糕，每个蛋糕有一个正整数的重量。
 * <p>
 * 早上，糕点铺已经做好了m个蛋糕。
 * <p>
 * 有一个顾客要来买两个蛋糕，他希望买这一天糕点铺中最重的和最轻的蛋糕，并且希望这两个蛋糕的重量恰好为a和b。剩余的n-m个蛋糕可以现烤，请问小团能否满足他的要求？
 * <p>
 * <p>
 * 输入包含多组数据，每组数据两行。
 * <p>
 * 每组数据的第一行包含4个整数，n,m,a,b，空格隔开。这里不保证a和b的大小关系。
 * <p>
 * 接下来一行m个数，空格隔开，代表烤好的蛋糕重量。
 * <p>
 * 1≤n,m,a,b≤1000 , m≤n , 蛋糕重量不会超过1000
 */
public class Meituan1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please input n,m,a,b:");
        // n（最多可以烤的数量） - m（已经做好的数量） = 剩余可以可烤的蛋糕数
        //a 最重的蛋糕
        //b 最轻的蛋糕
        String[] str = sc.nextLine().split(" ");

        System.out.println("already cack：");
        String[] alreadyCack = sc.nextLine().split(" ");
    }


    public static String isRequire(int n, int m, int a, int b) {
        return "";
    }
}
