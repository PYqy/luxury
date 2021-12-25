package cn.yqy.stack;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        StackLinked s = new StackLinked();
        boolean flag = true;
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.println("show 展示栈中数据");
            System.out.println("push 添加栈中数据");
            System.out.println("pop  弹出栈中数据");
            System.out.println("exit 退出栈");
            key = sc.next();
            switch (key) {
                case "show":
                    s.show();
                    break;
                case "push":
                    System.out.println("请输入要添加的数据");
                    int value = sc.nextInt();
                    s.push(value);
                    break;
                case "pop":
                    try {
                        int v = s.pop();
                        System.out.println("弹出的数据是：" + v);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    flag = false;
                    break;
                default:
                    System.out.println("请重新输入");

            }
        }

    }
}

class StackLinked {
    private Node headNode = new Node(-1);
    //private int top = -1;


    //is Empty
    public boolean isEmpty() {
        return headNode.getNext() == null;
    }

    //push
    public void push(int value) {
        if (isEmpty()) {
            headNode.setNext(new Node(value));
            return;
        }
        Node temp = headNode;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(new Node(value));
    }

    //pop
    public int pop() {
        if (headNode.getNext() == null) {
            throw new RuntimeException("linkedList is null，please try again later");
        }
        Node temp = headNode;
        while (true) {
            if (temp.getNext().getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        int value = temp.getNext().getNo();
        temp.setNext(null);
        return value;

    }

    //show
    public void show() {
        if (headNode.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        Node temp = headNode.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.getNo());
            temp = temp.getNext();
        }
    }


    public Node getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node headNode) {
        this.headNode = headNode;
    }


}

class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
