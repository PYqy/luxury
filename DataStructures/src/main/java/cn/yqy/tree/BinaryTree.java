package cn.yqy.tree;

/**
 * 二叉树 前中后序遍历 和查找
 */
public class BinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");
        Tree binaryTree = new Tree(root);

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRootNode(root);
        //测试
//        System.out.println("前序遍历"); // 1,2,3,5,4
//        binaryTree.preOrder();
//        //测试
//        System.out.println("中序遍历");
//        binaryTree.infixOrder(); // 2,1,5,3,4
//        //
//        System.out.println("后序遍历");
//        binaryTree.lastOrder(); // 2,5,4,3,1

//        Node node = binaryTree.preOrderSearch(5);
//        System.out.println(node.getNo());
//        node = binaryTree.infixOrderSearch(5);
//        System.out.println(node.getNo());
//        node = binaryTree.lastOrderSearch(5);
//        System.out.println(node.getNo());
        binaryTree.preOrder(); // 1,2,3,5,4
        binaryTree.delNode(5); //
        //binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4

    }


}


class Tree {
    private Node rootNode;

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

    public Tree(Node rootNode) {
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
