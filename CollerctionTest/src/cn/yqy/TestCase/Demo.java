package cn.yqy.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class Demo {
    public static void main(String[] args) {
        ArrayList<String> poker = new ArrayList<>();
        poker.add("大王");
        poker.add("小王");
        String[] colors = {"黑桃️", "红芯️", "方片️", "梅花️"};
        String[] pokerPai = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        for (String c : colors) {
            for (String pokerpai : pokerPai) {
                poker.add(c + pokerpai);
            }

        }
        Collections.shuffle(poker);
        ArrayList<String> player01 = new ArrayList<>();
        ArrayList<String> player02 = new ArrayList<>();
        ArrayList<String> player03 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();
        for (int i = 0; i < poker.size(); i++) {
            String p = poker.get(i);
            if (i >= 51) {
                dipai.add(p);
            } else if (i % 3 == 0) {
                player01.add(p);
            } else if (i % 3 == 1) {
                player02.add(p);
            } else if (i % 3 == 2) {
                player03.add(p);
            }

        }
        System.out.println(player01);
        System.out.println(player02);
        System.out.println(player03);
        System.out.println(dipai);

    }
}
