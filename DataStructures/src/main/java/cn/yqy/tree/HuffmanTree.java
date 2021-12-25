package cn.yqy.tree;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huff = new HuffmanTree();
        NodeToTree root = huff.createHuffmanTree(arr);
        root.preSort();
    }

    public NodeToTree createHuffmanTree(int[] arr) {
        List<NodeToTree> list = new ArrayList<NodeToTree>();
        for (int i : arr) {
            list.add(new NodeToTree(i));
        }

        while (list.size() > 1) {
            Collections.sort(list);
            NodeToTree left = list.get(0);
            NodeToTree right = list.get(1);
            NodeToTree parent = new NodeToTree(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);

    }


}

class NodeToTree implements Comparable<NodeToTree> {
    private int value;
    private NodeToTree left;
    private NodeToTree right;

    public void preSort() {
        System.out.println(this.getValue());
        if (this.getLeft() != null) {
            this.getLeft().preSort();
        }
        if (this.getRight() != null) {
            this.getRight().preSort();
        }
    }

    @Override
    public String toString() {
        return "NodeToTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public NodeToTree(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodeToTree getLeft() {
        return left;
    }

    public void setLeft(NodeToTree left) {
        this.left = left;
    }

    public NodeToTree getRight() {
        return right;
    }

    public void setRight(NodeToTree right) {
        this.right = right;
    }


    @Override
    public int compareTo(NodeToTree o) {
        return this.value - o.value;
    }
}
