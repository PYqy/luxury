package cn.yqy.recursion;

public class Migong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("getWay after");
        if (getWay(map, 1, 1)) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j] + "  ");
                }
                System.out.println();
            }
        }

    }

    //策略：右-》下-》左-》上
    public static boolean getWay(int[][] map, int i, int j) {
        //已经找到
        if (map[6][5] == 2) {
            return true;
        }

        if (map[i][j] == 0) {
            map[i][j] = 2;
            if (getWay(map, i + 1, j)) {
                return true;
            } else if (getWay(map, i, j + 1)) {
                return true;

            } else if (getWay(map, i - 1, j)) {
                return true;

            } else if (getWay(map, i, j - 1)) {
                return true;

            } else {
                map[i][j] = 3;
                return false;
            }

        } else {
            return false;
        }
    }
}
