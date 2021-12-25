package cn.yqy.kmp;

public class violenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        str2.hashCode();
        Object o = new Object();
        o.hashCode();
        int index = violence(str1, str2);
        System.out.println("index=" + index);

    }

    public static int violence(String str1, String str2) {
        int str1len = str1.length();
        int str2len = str2.length();
        System.out.println(str1len + "=======" + str2len);
        int i = 0, j = 0 ;
        while(i < str1len && j < str2len) {
            System.out.println(i+"----"+j);
            if(str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {

                i = i - j + 1 ;
                j = 0;
            }
        }
        if( j == str2len) {
            return i-j;
        } else {
            return -1;
        }
    }
}
