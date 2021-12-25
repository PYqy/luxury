package cn.yqy.exercise;

import java.util.HashMap;
import java.util.Map;

public class ArrayrepetitionPage95 {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 3, 3, 2, 1, 1, 5};
        Map<Integer, Integer> repetition = repetition(arr);
        for (Map.Entry<Integer, Integer> entry : repetition.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());

        }


    }

    public static Map<Integer, Integer> repetition(int arr[]) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, i);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count = 0;
            for (int i : arr) {
                if (i == entry.getKey()) {
                    map.put(i, ++count);
                }
            }
        }
        return map;

    }
}
