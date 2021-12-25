package cn.yqy.interviewprogram;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用面向对象的方法求出数组中重复value的个数，按如下数输出：
 * <p>
 * 1出现：1次
 * 3出现：2次
 * 。。。
 * <p>
 * int arr[] = {1,4,1,4,2,5,4,5,8,7,8,77,88,5,4,9,6,2,4,1,5}
 */

public class ArrayInterviewP95 {

    public static void main(String[] args) {
        int arr[] = {1, 4, 1, 4, 2, 5, 4, 5, 8, 7, 8, 77, 88, 5, 4, 9, 6, 2, 4, 1, 5};
        elementCount(arr);
    }

    public static void elementCount(int arr[]) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            Integer num = arr[i];
            if (!counts.containsKey(num)) {
                counts.put(num, 0);

            }
        }
        Set<Map.Entry<Integer, Integer>> set = counts.entrySet();

        for (Map.Entry<Integer, Integer> map : set) {
            int count = 0;
            int key = map.getKey();
            for (int i = 0; i < arr.length; i++) {
                if (key == arr[i]) {
                    count++;
                }
            }
            counts.put(key, count);

        }

        for (Map.Entry<Integer, Integer> m : counts.entrySet()) {
            System.out.println(m.getKey() + "出现:" + m.getValue() + "ci");
        }

    }
}
