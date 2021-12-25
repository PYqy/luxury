package cn.yqy.test;

import java.math.BigDecimal;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//       double a = 100;
//       double b = 0;
//        for (int i = 2; i <= 10; i++) {
//            a *= 0.5;
//            b += 2*a;
//        }
//        System.out.println("共经过"+Math.round(b+100)+"米");
//        System.out.println("第十次反弹"+(a)+"米");
//        String s = ")())(((())((())(";
//        int i = request1(s);
//        System.out.println(i);
//        String a = "damdfac";
//        String b = "aadafcefc";
//        String resutString = findComString(a, b);
//        System.out.println(resutString);
    }
    public static boolean request2(String str) {
        Map<String,Integer> maps = new HashMap<>();
        String c = "";
        for(int i = 0 ; i < str.length() ; i ++) {
            if(!maps.containsKey(c = str.charAt(i)+"")) {
                maps.put(c,0);
            }
        }
        Set<Map.Entry<String,Integer>> set = maps.entrySet();
        for (Map.Entry<String,Integer> map : set) {
            int count = 0;
            String key = map.getKey();
            for (int i = 0; i < str.length(); i++) {
                if (key == str.charAt(i)+"") {
                    count++;
                }
            }
            maps.put(key, count);
        }
        Set<Map.Entry<String,Integer>> set2 = maps.entrySet();
        Iterator<Map.Entry<String, Integer>> it = set2.iterator();
        int value = 0;
        if(it.hasNext()) {
             value = it.next().getValue();
        }
        while(it.hasNext()) {
            if(value != it.next().getValue()) {
                return false ;
            }
        }


        if (maps.size() > 3) {
            return true;
        }
        return false;

    }

    public static int request1(String str) {
        char c;
        int res = 0;
        Stack<String> cal = new Stack<String>();
        for(int i = 0 ; i < str.length() ; i++) {
            if( (c = (str.charAt(i))) == '(' ) {
                cal.push(c+"");
            } else if( (c = (str.charAt(i))) == ')') {
                if( cal.size() > 0) {
                    cal.pop();
                    res++;
                }
            }
        }
        return res;
    }

    public static String request3(String a, String b) {
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    int maxLen = (i == 0 || j == 0) ? 0 : matrix[i - 1][j - 1];
                    matrix[i][j] = maxLen + 1;
                } else {
                    int maxLen = (i == 0 || j == 0) ? 0 : Math.max(
                            matrix[i - 1][j], matrix[i][j - 1]);
                    matrix[i][j] = maxLen;
                }
            }
        }
        int i = a.length() - 1;
        int j = b.length() - 1;
        ArrayList<Character> restList = new ArrayList<Character>();
        while (i >= 0 && j >= 0) {
            if (a.charAt(i) == b.charAt(j)) {
                restList.add(a.charAt(i));
                i--;
                j--;
            } else {
                if (matrix[i - 1][j] >= matrix[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        StringBuilder reBuilder = new StringBuilder(restList.size());
        for (int k = restList.size() - 1; k >= 0; k--) {
            reBuilder.append(restList.get(k));
        }
        return reBuilder.toString();
    }


}
