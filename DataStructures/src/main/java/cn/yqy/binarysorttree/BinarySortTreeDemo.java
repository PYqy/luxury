package cn.yqy.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.addNode(new Node(arr[i]));
        }
        //中序遍历二叉排序树
//        System.out.println("中序遍历二叉排序树~");
//        binarySortTree.preList(); // 1, 3, 5, 7, 9, 10, 12


        binarySortTree.delNode(12);
        binarySortTree.delNode(5);

        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(7);

        binarySortTree.delNode(9);
        binarySortTree.delNode(10);
        binarySortTree.preList();

    }
}

class BinarySortTree {
    Node root;

    public BinarySortTree() {
    }

    /**
     * 对删除节点的情况分析
     * <p>
     * <p>
     * 第一种情况:
     * 删除叶子节点 (比如:2, 5, 9, 12)
     * 思路
     * (1) 需求先去找到要删除的结点 targetNode
     * (2) 找到 targetNode 的 父结点 parent
     * (3) 确定 targetNode 是 parent 的左子结点 还是右子结点
     * (4) 根据前面的情况来对应删除
     * 左子结点 parent.left = null
     * 右子结点 parent.right = null;
     * <p>
     * <p>
     * 第二种情况: 删除只有一颗子树的节点 比如 1
     * 思路
     * (1) 需求先去找到要删除的结点 targetNode
     * (2) 找到 targetNode 的 父结点 parent
     * (3) 确定 targetNode 的子结点是左子结点还是右子结点 (4) targetNode 是 parent 的左子结点还是右子结点
     * (5) 如果 targetNode 有左子结点
     * 5. 1 如果 targetNode 是 parent 的左子结点
     * parent.left = targetNode.left;
     * 5.2 如果 targetNode 是 parent 的右子结点 parent.right = targetNode.left;
     * (6) 如果 targetNode 有右子结点
     * 6.1 如果 targetNode 是 parent 的左子结点 parent.left = targetNode.right;
     * 6.2 如果 targetNode 是 parent 的右子结点 parent.right = targetNode.right
     * <p>
     * <p>
     * 情况三 : 删除有两颗子树的节点. (比如:7, 3，10 ) 思路
     * (1) (2) (3) (4) (5) (6)
     * 需求先去找到要删除的结点 targetNode 找到 targetNode 的 父结点 parent
     * 从 targetNode 的右子树找到最小的结点
     * 用一个临时变量，将 最小结点的值保存 temp = 11 删除该最小结点
     * targetNode.value = temp
     *
     * @param value
     * @return
     */


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

    public BinarySortTree(Node root) {
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
