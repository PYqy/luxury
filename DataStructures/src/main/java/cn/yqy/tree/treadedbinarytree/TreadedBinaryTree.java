package cn.yqy.tree.treadedbinarytree;

public class TreadedBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "jack2");
        Node node4 = new Node(8, "jac3");
        Node node5 = new Node(10, "jack4");
        Node node6 = new Node(14, "jack6");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);


        ThreadedTree threadedTree = new ThreadedTree(root);
        //threadedTree.threadedTree();

        //threadedTree.threadedTree();
        //threadedTree.threadedTreePre();
        threadedTree.threadedTreeLast();
        Node leftNode = node6.getLeft();
        Node rightNode = node3.getRight();
        System.out.println(leftNode);
        System.out.println("14 号结点的前驱结点是 =" + leftNode.getNo());
        System.out.println("6 号结点的后继结点是=" + rightNode.getNo()); //1
        // System.out.println("10 号结点的前驱结点是 =" + leftNode.getNo());
        //  System.out.println("10 号结点的后继结点是=" + rightNode.getNo()); //1
        //threadedTree.threadedTreeMiddleList();
        //threadedTree.threadedTreePreList();
        threadedTree.threadedTreeLastList();

    }
}

class ThreadedTree {
    private Node rootNode;
    private Node pre = null;

    public void threadedTreeMiddleList() {
        threadedTreeMiddleList(rootNode);

    }//

    //后续不对！！不会
    public void threadedTreeLastList() {
        threadedTreeLastList(rootNode);
    }

    public void threadedTreeLastList(Node node) {
        while (node != null) {
            while (!node.isPreNode()) {
                node = node.getLeft();
            }

            while (node.isLastNode()) {

                System.out.println(node.getNo());
                node = node.getRight();
            }
            node = node.getRight();
            System.out.println(node.getNo());
        }

    }


    //last
    public void threadedTreeLast() {
        threadedTreeLast(rootNode);
    }

    public void threadedTreeLast(Node node) {
        if (node == null) {
            return;
        }
        if (!node.isPreNode())
            threadedTreeLast(node.getLeft());
        if (!node.isLastNode())
            threadedTreeLast(node.getRight());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setPreNode(true);

        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setLastNode(true);
        }
        pre = node;
    }


    //prelist
    public void threadedTreePreList() {
        threadedTreePreList(rootNode);

    }

    public void threadedTreePreList(Node node) {
        while (node != null) {
            System.out.println(node.getNo());
            while (!node.isPreNode()) {
                node = node.getLeft();
                System.out.println(node.getNo());
            }
            while (node.isLastNode()) {
                node = node.getRight();
                System.out.println(node.getNo());
            }
            node = node.getRight();
        }
    }

    //pre threadedtree
    public void threadedTreePre() {
        threadedTreePre(rootNode);

    }

    public void threadedTreePre(Node node) {

        if (node == null) {
            return;
        }

        //左指针为空,将左指针指向前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setPreNode(true);
        }

        //前一个节点的后继节点指向当前节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setLastNode(true);
        }

        pre = node;

        //处理左子树
        if (!node.isPreNode()) {
            threadedTreePre(node.getLeft());
        }

        //处理右子树
        if (!node.isLastNode()) {
            threadedTreePre(node.getRight());
        }


    }

    //middle list
    public void threadedTreeMiddleList(Node node) {
        while (node != null) {
            while (!node.isPreNode()) {
                node = node.getLeft();
            }
            System.out.println(node.getNo());
            while (node.isLastNode()) {
                node = node.getRight();
                System.out.println(node.getNo());
            }
            node = node.getRight();
        }

    }

    //middle
    public void threadedTree() {

        threadedTree(rootNode);

    }

    //middle
    public void threadedTree(Node node) {
        if (node == null) {
            return;
        }

        threadedTree(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setPreNode(true);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setLastNode(true);
        }
        pre = node;


        threadedTree(node.getRight());


    }


    //del
    public void delNode(int no) {
        if (rootNode != null) {
            if (rootNode.getNo() == no) {
                rootNode = null;

            } else {
                rootNode.delNode(no);
            }
        } else {
            System.out.println("为空树不能删除");
        }

    }

    //infixOrder
    public void infixOrder() {
        if (this.rootNode != null) {
            this.rootNode.infixOrder();
        } else {
            System.out.println("this tree is Empty");
        }


    }

    //preOrder
    public void preOrder() {
        if (this.rootNode != null) {
            this.rootNode.preOrder();
        } else {
            System.out.println("this tree is Empty");
        }

    }

    //lastOrder
    public void lastOrder() {
        if (this.rootNode != null) {
            this.rootNode.lastOrder();
        } else {
            System.out.println("this tree is Empty");
        }

    }

    public Node preOrderSearch(int no) {
        if (this.rootNode != null) {
            return this.rootNode.preOrderSearch(no);
        } else {
            System.out.println("this tree is Empty");
            return null;
        }
    }

    public Node infixOrderSearch(int no) {
        if (this.rootNode != null) {
            return this.rootNode.infixOrderSearch(no);
        } else {
            System.out.println("this tree is Empty");
            return null;
        }
    }

    public Node lastOrderSearch(int no) {
        if (this.rootNode != null) {
            return this.rootNode.lastOrderSearch(no);
        } else {
            System.out.println("this tree is Empty");
            return null;
        }
    }

    public ThreadedTree(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}

class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;
    private boolean isPreNode = false;
    private boolean isLastNode = false;

    public boolean isPreNode() {
        return isPreNode;
    }

    public void setPreNode(boolean preNode) {
        isPreNode = preNode;
    }

    public boolean isLastNode() {
        return isLastNode;
    }

    public void setLastNode(boolean lastNode) {
        isLastNode = lastNode;
    }

    //deleNode
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {

            this.left = null;
            return;
        }
        if (this.right != null && this.left.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //infixOrder iterator
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.getNo() + " " + this.getName());
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    //preOrder iterator
    public void preOrder() {
        System.out.println(this.getNo() + " " + this.getName());
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    //lastOrder iterator
    public void lastOrder() {
        if (this.left != null) {
            this.left.lastOrder();
        }
        if (this.right != null) {
            this.right.lastOrder();
        }
        System.out.println(this.getNo() + " " + this.getName());
    }

    //preOrder Serach
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(no);

        }
        return node;
    }

    //infixOrder Serach
    public Node infixOrderSearch(int no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);

        }
        return node;
    }

    public Node lastOrderSearch(int no) {
        Node node = null;
        if (this.left != null) {
            node = this.left.lastOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.lastOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        } else {
            return null;
        }

    }


    public Node(int no, String name) {
        this.no = no;
        this.name = name;
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

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

