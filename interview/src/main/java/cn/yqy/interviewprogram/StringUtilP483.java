package cn.yqy.interviewprogram;

/**
 * 编写一个工具类StringUtil，提供方法 int compare（char[] v1,char[] v2)比较字符串v1，v2，如果按照字符顺序 v1》v2 return 1 ；== return 0
 */
public class StringUtilP483 {
    public int compare(char[] v1, char[] v2) {
        String s1 = new String(v1);
        String s2 = new String(v2);
        int result = s1.compareTo(s2);
        return result == 0 ? 0 : (result > 0 ? 1 : -1);
    }

}
