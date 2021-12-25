package cn.yqy.doublelinked;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedListNode d1 = new DoubleLinkedListNode(1, "", "");
        DoubleLinkedListNode d5 = new DoubleLinkedListNode(1, "", "");
        DoubleLinkedListNode d2 = new DoubleLinkedListNode(2, "zhang3", "3");
        DoubleLinkedListNode d3 = new DoubleLinkedListNode(5, "li4", "4");
        DoubleLinkedListNode d4 = new DoubleLinkedListNode(3, "zhao6", "6");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.setHeadNode(d1);
        doubleLinkedList.addNode(d1, d2);
        doubleLinkedList.addNode(d1, d3);
        doubleLinkedList.addNode(d1, d4);
        doubleLinkedList.list(d1);
        System.out.println("有序链表");
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();
        doubleLinkedList2.setHeadNode(d5);
        doubleLinkedList2.addSortNode(d5, d2);
        doubleLinkedList2.addSortNode(d5, d3);
        doubleLinkedList2.addSortNode(d5, d4);
        doubleLinkedList2.list(d5);

        doubleLinkedList2.delNode(doubleLinkedList2.getHeadNode(), 3);
        System.out.println("------");
        doubleLinkedList2.list(doubleLinkedList2.getHeadNode());
        doubleLinkedList2.updateNode(doubleLinkedList2.getHeadNode(), new DoubleLinkedListNode(2, "zhang2", "2"));
        System.out.println("------");
        doubleLinkedList2.list(doubleLinkedList2.getHeadNode());


    }

}

class DoubleLinkedList {
    private DoubleLinkedListNode headNode;

    public DoubleLinkedListNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(DoubleLinkedListNode headNode) {
        this.headNode = headNode;
    }

    //判断链表是否为null  为null true  else false
    public boolean isEmpty(DoubleLinkedListNode headNode) {
        if (headNode.getNext() == null) {
            return true;
        }
        return false;
    }

    //无序添加节点
    public void addNode(DoubleLinkedListNode headNode, DoubleLinkedListNode newNode) {

        if (newNode == null) {
            System.out.println("需要添加的节点为空，不能修改");
            return;
        }
        DoubleLinkedListNode temp = headNode;
        boolean flag = false;
        while (temp.getNext() != null) {
            if (temp.getNext().getNo() == newNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();

        }
        if (flag) {
            System.out.println(newNode.getNo() + "重复");
            return;
        } else {
            newNode.setPre(temp);
//            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }
    }
    //有序添加节点

    public void addSortNode(DoubleLinkedListNode headNode, DoubleLinkedListNode newNode) {
        DoubleLinkedListNode temp = headNode;
        boolean flag = false;
        if (newNode == null) {
            System.out.println("需要添加的节点为空，不能修改");
            return;
        }


        while (true) {
            if (temp.getNext() == null) {
                break;
            }

            if (temp.getNext().getNo() > newNode.getNo()) {
                break;

            }
            if (temp.getNext().getNo() == newNode.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }


        if (flag) {
            System.out.println(newNode.getNo() + "重复");
            return;
        } else {
            newNode.setNext(temp.getNext());
            newNode.setPre(temp);
            temp.setNext(newNode);
        }


    }

    //修改节点
    public void updateNode(DoubleLinkedListNode headNode, DoubleLinkedListNode newNode) {
        if (isEmpty(headNode)) {
            System.out.println("该链表为空，不能修改");
            return;
        }
        if (newNode == null) {
            System.out.println("需要修改的节点为空，不能修改");
            return;
        }

        DoubleLinkedListNode temp = headNode.getNext();

        while (temp != null) {
            if (newNode.getNo() == temp.getNo()) {
                temp.setName(newNode.getName());
//                temp.setPre(newNode.getPre());
//                temp.setNext(newNode.getNext());
                temp.setNickName(newNode.getNickName());
                break;

            }

            temp = temp.getNext();
        }
    }

    //删除节点
    public void delNode(DoubleLinkedListNode headNode, int no) {
        if (isEmpty(headNode)) {
            System.out.println("该链表为空，不能修改");
            return;
        }
        DoubleLinkedListNode temp = headNode.getNext();
        boolean flag = false;
        while (temp != null) {

            if (temp.getNo() == no) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.getPre().setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPre(temp.getPre());
            }
            System.out.println(temp + "删除成功");
            return;
        } else {
            System.out.println(no + "没有找到");
        }
    }

    //显示链表
    public void list(DoubleLinkedListNode headNode) {
        if (isEmpty(headNode)) {
            System.out.println("该链表为空");
            return;
        }
        DoubleLinkedListNode temp = headNode.getNext();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }
}

class DoubleLinkedListNode {
    private DoubleLinkedListNode headNode;
    private int no;
    private String name;
    private String nickName;
    private DoubleLinkedListNode pre;
    private DoubleLinkedListNode next;

    public DoubleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleLinkedListNode{" +
                "headNode=" + headNode +
                ", no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public DoubleLinkedListNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(DoubleLinkedListNode headNode) {
        this.headNode = headNode;
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

    public DoubleLinkedListNode getPre() {
        return pre;
    }

    public void setPre(DoubleLinkedListNode pre) {
        this.pre = pre;
    }

    public DoubleLinkedListNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkedListNode next) {
        this.next = next;
    }
}
