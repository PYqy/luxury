package cn.yqy.tree;

import java.util.*;

/**
 * 不保证正确性  剩余部分跳过
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String data = "i like like like java do you like a java";
        byte[] dataBytes = data.getBytes();
        HuffmanCode code = new HuffmanCode();
        List<HuffmanNode> nodes = code.getNodes(dataBytes);
//        for (HuffmanNode node : nodes) {
//            System.out.println(node);
//
//        }
        HuffmanNode huffman = code.createHuffman(nodes);
        code.preHuffman(huffman);
        code.getCodes(huffman);
        for (Map.Entry<Byte, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());

        }


    }

    static Map<Byte, String> map = new HashMap<>();
    static StringBuilder builder = new StringBuilder();

    public void getCodes(HuffmanNode node) {
        if (node == null) {
            return;
        }
        generateHuffmanCode(node.getLeft(), "0", builder);
        generateHuffmanCode(node.getRight(), "1", builder);

    }

    public void generateHuffmanCode(HuffmanNode node, String code, StringBuilder builder) {
        StringBuilder builder1 = new StringBuilder(builder);
        if (node != null) {
            if (node.getData() == null) {
                builder1.append(code);
                generateHuffmanCode(node.getLeft(), "0", builder1);
                generateHuffmanCode(node.getRight(), "1", builder1);
            } else {
                map.put(node.getData(), builder1.toString());
            }


        }


    }

    public HuffmanNode createHuffman(List<HuffmanNode> nodes) {

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode left = nodes.get(0);
            HuffmanNode right = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(null, left.getWeigth() + right.getWeigth());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public void preHuffman(HuffmanNode root) {
        if (root == null) {
            System.out.println("huffman is null");
            return;
        } else {
            root.preList();
        }

    }

    public List<HuffmanNode> getNodes(byte[] dataBytes) {
        Map<Byte, Integer> map = new HashMap<>();
        List<HuffmanNode> list = new ArrayList<>();
        for (byte dataByte : dataBytes) {
            Integer count = map.get(dataByte);
            if (map.containsKey(dataByte)) {
                map.put(dataByte, ++count);
            } else {
                map.put(dataByte, 1);
            }

        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {

            list.add(new HuffmanNode(entry.getKey(), entry.getValue()));

        }
        return list;


    }


}

class HuffmanNode implements Comparable<HuffmanNode> {
    private Byte data;
    private Integer weigth;
    private HuffmanNode left;
    private HuffmanNode right;

    public void preList() {
        System.out.println("HuffmanNode{" +
                "data=" + this.data +
                ", weigth=" + this.weigth +
                '}');
        if (this.left != null) {
            this.left.preList();
        }
        if (this.right != null) {
            this.right.preList();
        }
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weigth=" + weigth +
                '}';
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    public HuffmanNode(Byte data, Integer weigth) {

        this.data = data;
        this.weigth = weigth;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weigth - o.weigth;
    }
}
