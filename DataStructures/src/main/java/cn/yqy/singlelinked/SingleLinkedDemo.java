package cn.yqy.singlelinked;

import sun.security.util.Length;

import java.util.List;
import java.util.Stack;

public class SingleLinkedDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(2, "lis4", "4");
        HeroNode hero2 = new HeroNode(3, "wang5", "5");
        HeroNode hero3 = new HeroNode(4, "zhao6", "6");
        HeroNode hero4 = new HeroNode(1, "zhao6", "6");

        HeroNode hero5 = new HeroNode(8, "lis4", "4");
        HeroNode hero6 = new HeroNode(4, "wang5", "5");
        HeroNode hero7 = new HeroNode(9, "zhao6", "6");
        HeroNode hero8 = new HeroNode(10, "zhao6", "6");

        SingleLinked singleLinked = new SingleLinked();
        SingleLinked singleLinked2 = new SingleLinked();
        SingleLinked singleLinked3 = new SingleLinked();
        singleLinked.addOrder(hero1);
        singleLinked.addOrder(hero2);
        singleLinked.addOrder(hero3);
        singleLinked.addOrder(hero4);
        singleLinked2.addOrder(hero5);
        singleLinked2.addOrder(hero6);
        singleLinked2.addOrder(hero7);
        singleLinked2.addOrder(hero8);
        singleLinked.list();
        singleLinked2.list();
        System.out.println("--------");
        singleLinked3.mergeSortLinkedList(singleLinked.getHeadNode(), singleLinked2.getHeadNode());
        singleLinked3.list();

        //singleLinked.list();

//        HeroNode hero5 = new HeroNode(2,"lis5","555");
//        singleLinked.update(hero5);
//        singleLinked.list();
//        singleLinked.deleteNode(3);
//        singleLinked.list();
//        System.out.println("-------------------");
//        singleLinked.addOrder(hero2);
//        singleLinked.addOrder(hero3);
//         System.out.println(singleLinked.findLastIndexNode(1,singleLinked.getHeadNode()));
//        singleLinked.list();
//        System.out.println("长度是:"+singleLinked.getLength(singleLinked.getHeadNode()));
//        System.out.println("=---------");
//        singleLinked.reversetList(singleLinked.getHeadNode());
//        singleLinked.list();


        // singleLinked.reversePrint(singleLinked.getHeadNode());

    }
}

class SingleLinked {
    HeroNode headNode = new HeroNode(1, "", "");

    public void setHeadNode(HeroNode headNode) {
        this.headNode = headNode;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1、找到当前链表的最后节点
     * 2、将最后这个节点的next指向新的节点
     *
     * @param heroNode
     */

    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = headNode;
        //遍历链表，找到最后
        while (true) {
            //找到链表最后
            if (temp.getNext() == null) {
                break;


            } else {
                //如果没有找到最后，将temp后移
                temp = temp.getNext();
            }
        }
        //当退出while循环时，temp就指向了链表的最后
        //当最后这个节点next指向 新的节点
        temp.setNext(heroNode);

    }

    //按no大小排序添加节点
    public void addOrder(HeroNode heroNode) {
        HeroNode temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == heroNode.getNo()) {
                flag = true;
                break;
            }
            if (temp.getNext().getNo() > heroNode.getNo()) {
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            System.out.println("no重复");

        } else {
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }

    //修改节点 不能修改 no
    public void update(HeroNode newHeroNode) {
        if (headNode.getNext() == null) {
            System.out.println("lingked list is null");
            return;
        }
        HeroNode temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setName(newHeroNode.getName());
            temp.setNickName(newHeroNode.getNickName());
        } else {
            System.out.println("没有找到节点" + newHeroNode.getNo());
        }
    }

    //删除节点
    public void deleteNode(int no) {
        if (headNode.getNext() == null) {
            System.out.println("this linkedList is null");
            return;
        }
        boolean res = false;
        HeroNode temp = headNode;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == no) {
                res = true;
                break;
            }

            temp = temp.getNext();

        }
        if (res) {
            temp.setNext(temp.getNext().getNext());
        }

    }

    //查找倒数第几个下标的 节点
    public HeroNode findLastIndexNode(int index, HeroNode headNode) {
        if (headNode.getNext() == null) {
            return null;
        }
        int size = new SingleLinked().getLength(headNode);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = headNode.getNext();
        for (int i = 0; i < size - index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    //获取链表长度
    public int getLength(HeroNode headNode) {

        if (headNode.getNext() == null) {
            return 0;
        }
        HeroNode temp = headNode;
        int length = 0;

        while (temp.getNext() != null) {
            length++;
            temp = temp.getNext();
        }


        return length;

    }

    //链表反转
    public void reversetList(HeroNode headNode) {
        if (headNode.getNext() == null || headNode.getNext().getNext() == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode reverseNode = new HeroNode(1, "", "");
        HeroNode temp = headNode.getNext();
        HeroNode temp2 = null;
        while (temp != null) {

            temp2 = temp.getNext();
            temp.setNext(reverseNode.getNext());
            reverseNode.setNext(temp);
            temp = temp2;

        }
        headNode.setNext(reverseNode.getNext());
    }

    //反转显示链表
    public void reversePrint(HeroNode headNode) {
        if (headNode.getNext() == null) {
            System.out.println("this LinkedList is numm, please again attempt ");
            return;
        }
        HeroNode temp = headNode.getNext();
        Stack<HeroNode> stack = new Stack<HeroNode>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();

        }
        while (stack.size() > 0) { //
            System.out.println(stack.pop() + "出栈");
        }
    }

    /**
     * 将 两个有序链表合并
     */
    public void mergeSortLinkedList(HeroNode headNode1, HeroNode headNode2) {
        if (headNode1.getNext() == null || headNode2.getNext() == null) {
            System.out.println("LinkedList1 And LinkedList2 not exist");
            return;
        }
        HeroNode h = new HeroNode(1, "", "");
        HeroNode finalHeadNode = h;
        HeroNode temp1 = headNode1.getNext();
        HeroNode temp2 = headNode2.getNext();

        while (temp1 != null && temp2 != null) {

            if (temp1.getNo() < temp2.getNo()) {

                finalHeadNode.setNext(temp1);
                finalHeadNode = finalHeadNode.getNext();
                temp1 = temp1.getNext();
            } else {
                finalHeadNode.setNext(temp2);
                finalHeadNode = finalHeadNode.getNext();
                temp2 = temp2.getNext();
            }


        }
        while (temp1 != null) {


            finalHeadNode.setNext(temp1);
            finalHeadNode = finalHeadNode.getNext();
            temp1 = temp1.getNext();


        }
        while (temp2 != null) {

            finalHeadNode.setNext(temp2);
            finalHeadNode = finalHeadNode.getNext();
            temp2 = temp2.getNext();

        }

//        while(h != null){
//            System.out.println(h);
//            h = h.getNext();
//        }

        headNode.setNext(h.getNext());

    }

    //显示链表
    public void list() {
        if (headNode.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = headNode;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    public HeroNode getHeadNode() {
        return headNode;
    }

}

class HeroNode {
    private int no;
    private String name;
    private String nickName;
    private HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
