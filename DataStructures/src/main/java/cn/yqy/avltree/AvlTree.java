package cn.yqy.avltree;

public class AvlTree {

    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9}; //创建一个 AVLTree 对象
        Avl avlTree = new Avl(); //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.addNode(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.preList();
        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
    }
}

class Avl {

    Node root;


    public Avl() {
    }


    public void delNode(int value) {
        Node targetNode = searchTargetNode(value);
        if (targetNode == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        Node parentNode = searchParentNode(value);
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            //删除叶子结点


            if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                parentNode.setLeft(null);
            } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                parentNode.setRight(null);
            }
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            //删除左右节点都有的节点
            Node node = deMinNode(targetNode.getRight());
            targetNode.setValue(node.getValue());

        } else {
            //删除只有一个节点的
            if (targetNode.getLeft() != null) {
                if (parentNode != null) {
                    if (parentNode.getLeft().getValue() == value) {
                        parentNode.setLeft(targetNode.getLeft());

                    } else {
                        parentNode.setRight(targetNode.getLeft());
                    }
                } else {
                    root = targetNode.getLeft();
                }
            } else {
                if (parentNode != null) {
                    if (parentNode.getLeft().getValue() == value) {
                        parentNode.setLeft(targetNode.getRight());
                    } else {
                        parentNode.setRight(targetNode.getRight());
                    }
                } else {
                    root = targetNode.getRight();
                }
            }
        }
    }

    public Node deMinNode(Node node) {
        Node temp = node;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        delNode(node.getValue());
        return node;
    }


    public Node searchParentNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParentNode(value);
        }
    }

    public Node searchTargetNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchTargetNode(value);
        }
    }

    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    public void preList() {
        if (root == null) {
            System.out.println("this binarySortTree is null, please restart");
            return;
        } else {
            root.preList();
        }
    }

    public Avl(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}


class Node {
    private Integer value;
    private Node left;
    private Node right;

    public void leftRevolve(Node node) {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = this.right.left;
        this.left = newNode;
        this.value = this.right.value;

        this.right = this.right.right;

    }

    public void rightRevolve(Node node) {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    public int leftHeight() {
        return left == null ? 0 : left.height();
    }

    public int rightHeight() {
        return right == null ? 0 : right.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //查找目标节点的 父节点
    public Node searchParentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.getValue() == value)) {
            return this;

        } else {
            if (this.left != null && this.value > value) {
                return this.left.searchParentNode(value);
            } else if (this.right != null && this.getValue() <= value) {
                return this.right.searchParentNode(value);
            } else {
                return null;
            }

        }
    }

    //查找 目标节点
    public Node searchTargetNode(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.searchTargetNode(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.searchTargetNode(value);
            } else {
                return null;
            }
        }
    }

    public void preList() {
        if (this.left != null) {
            this.left.preList();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.preList();
        }
    }

    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.getValue() < this.getValue()) {
            if (this.getLeft() != null) {
                this.left.addNode(node);
            } else {
                this.left = node;
            }

        } else {
            if (this.right != null) {
                this.right.addNode(node);
            } else {
                this.right = node;
            }


        }
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRevolve(right);
                leftRevolve(this);
            } else {
                leftRevolve(node);
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.leftHeight() < left.rightHeight()) {
                left.leftRevolve(left);
                rightRevolve(this);
            } else {
                rightRevolve(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public Node(Integer value) {

        this.value = value;
    }
}