package cn.yqy.sperserarry;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //1\创建二维数组
        int[][] array = new int[11][11];
        array[1][2] = 2;
        array[2][4] = 11;
        array[4][5] = 12;
        int sum = 0;
        //找有效元素
        for (int[] row : array) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        int count = 0;
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = 3;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }


        System.out.println("得到稀疏矩阵:");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //将稀疏矩阵恢复成数组
        int[][] newArray = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {


            newArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }

        for (int[] row : newArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        DataInputStream dis = new DataInputStream(new FileInputStream("map.data"));
        try {
//                fos = new FileOutputStream("map.data");
//                dos = new DataOutputStream(fos);
//               for(int [] row:newArray){
//                   for(int data:row){
//                       dos.write(data);
//                   }
//               }
            int[][] array22 = new int[11][11];
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    array22[i][j] = dis.read();
                }
            }
            System.out.println("读出后数组");
            for (int[] row : newArray) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dis.close();
        }
    }
}
