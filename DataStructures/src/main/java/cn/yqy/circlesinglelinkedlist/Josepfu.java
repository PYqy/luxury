package cn.yqy.circlesinglelinkedlist;

import javax.xml.soap.Node;

/**
 * Josephu 问题为:设编号为 1，2，... n 的 n 个人围坐一圈，
 * 约定编号为 k(1<=k<=n)的人从 1 开始报数，
 * 数到 m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，
 * 依次类推，直到所有人出列为止，由此 产生一个出队编号的序列。
 */

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleList c = new CircleSingleList();
//        c.addBoy(5);
//        c.list();
//
//
//        c.countBoy(1,2,5);

        c.count(0);

    }
}

class CircleSingleList {
    private Boy first = null;

    //add Boy
    public void addBoy(int nums) {

        if (nums < 1) {
            System.out.println("请输入》1");
            return;
        }
        Boy currboy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                currboy = boy;
            } else {
                currboy.setNext(boy);
                boy.setNext(first);
                currboy = boy;
            }
        }

    }

    //根据用户输入，推出小孩出圈顺序

    /**
     * @param strayNO  开始的小孩no
     * @param countNum 每次数几下
     * @param nums     最初有几个小孩
     */
    public void countBoy(int strayNO, int countNum, int nums) {
        if (first == null || strayNO < 1 || strayNO > nums) {
            System.out.println("输入数值有误，请重新输入");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;

            }
            helper = helper.getNext();
        }

        for (int i = 0; i < strayNO - 1; i++) {
            first = first.getNext();
            helper.setNext(first);
        }
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                // helper.setNext(first);
                helper = helper.getNext();
            }
            System.out.println("出圈的小孩是" + first.getNo());
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("最后一个小孩是" + first.getNo());
    }

    public Boy getFirst() {
        return first;
    }


    public void count(int n) {
        int k = 3;
        //头节点不存储数据
        Boy head = new Boy(-1);
        Boy cur = head;
        //循环构造这个链表
        for (int i = 1; i <= n; i++) {
            Boy boy = new Boy(i);
            cur.setNext(boy);
            cur = boy;
        }

        //链表首尾呼应
        cur.setNext(head.getNext());
        Boy temp = head.getNext();
        //循环推出的条件最后只剩一个节点，也就是这个节点的下一个节点是它本身
        while (true) {
            if (temp.getNext() == temp) {
                break;
            }
            for (int i = 1; i < k - 1; i++) {
                temp = temp.getNext();

            }
            System.out.println(temp.getNext().getNo() + "->");
            temp.setNext(temp.getNext().getNext());
            temp = temp.getNext();
        }
        System.out.println(temp.getNo());
    }

    //iterator
    public void list() {
        if (first == null) {
            System.out.println("环形链表为空");
            return;
        }
        Boy boy = first;
        while (true) {
            System.out.println(boy.getNo());
            if (boy.getNext() == first) {
                break;
            }
            boy = boy.getNext();
        }
    }

}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy() {

    }

    public Boy(int no, Boy next) {

        this.no = no;
        this.next = next;
    }
}
