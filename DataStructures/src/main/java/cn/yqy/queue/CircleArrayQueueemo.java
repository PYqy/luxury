package cn.yqy.queue;

import java.util.Scanner;

public class CircleArrayQueueemo {
    public static void main(String[] args) {
        ArrayQueue2 queue = new ArrayQueue2(3);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        char value = ' ';
        while (loop) {
            System.out.println("s (show) 显示队列全部内容");
            System.out.println("a (add)  添加队列元素");
            System.out.println("p (pop)  出队列");
            System.out.println("h (head) 显示队列头内容");
            System.out.println("e (exit) 退出");
            System.out.println("请输入您的操作：");
            value = sc.next().charAt(0);

            switch (value) {

                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入您需要添加的数值：");
                    int data = sc.nextInt();
                    queue.addQueue(data);
                    break;
                case 'p':
                    int res2 = queue.getQueue();
                    System.out.println("取出的数据是：" + res2);
                    break;
                case 'h':
                    int res = queue.getQueueHead();
                    System.out.println("队列头是：" + res);
                    break;
                case 'e':
                    sc.close();
                    loop = false;

                    break;
                default:
                    break;
            }
        }


    }


}

class ArrayQueue2 {
    private int maxSize;//数组最大容量
    private int rear;//队尾
    private int front;//队头
    private int[] arr;

    public ArrayQueue2(int size) {
        maxSize = size;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加队列元素
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("The queue is full");
            return;
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }
        int data = arr[front];
        front = (front + 1) % maxSize;
        return data;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");

        }
        //从front开始遍历
        for (int i = front; i < front + ((rear + maxSize - front) % maxSize); i++) {

            System.out.println("arr[" + i % maxSize + "]=" + arr[i % maxSize]);

        }
    }

    //显示队列头信息 不是取信息
    public int getQueueHead() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }
        return arr[front % maxSize];
    }
}
