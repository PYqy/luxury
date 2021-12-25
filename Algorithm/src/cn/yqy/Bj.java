package cn.yqy;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

/**
 * UDP传输
 * DatagramPacket：相当于轮船
 * DatagramSocket：相当于两个码头
 */
public class Bj {
    public static void main(String args[]) {
        BjFrame bj = new BjFrame();

    }

    static class BjFrame extends JFrame implements Runnable, ActionListener {
        JTextField out_message = new JTextField(6);
        JTextArea in_message = new JTextArea();
        JButton send = new JButton("发送");

        public BjFrame() {
            setTitle("我是北京");
            setSize(400, 200);
            setVisible(true);
            send.addActionListener(this);
            JPanel pSouth = new JPanel();
            pSouth.add(out_message);
            pSouth.add(send);
            add(pSouth, "South");
            add(new JScrollPane(in_message), "Center");
            validate();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Thread thread = new Thread(this);
            thread.start();

        }


        @Override
        public void actionPerformed(ActionEvent e) {
            byte buffer[] = out_message.getText().trim().getBytes();
            try {
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket data_pack = new DatagramPacket(buffer, buffer.length, address, 5666);
                DatagramSocket mail_in = new DatagramSocket();
                mail_in.send(data_pack);


            } catch (Exception ea) {

            }
        }

        @Override
        public void run() {
            DatagramPacket pack = null;
            DatagramSocket mail_data = null;

            byte data[] = new byte[8192];
            try {
                pack = new DatagramPacket(data, data.length);
                mail_data = new DatagramSocket(5888);

            } catch (Exception e) {

            }
            while (true) {
                if (mail_data == null)
                    break;
                else
                    try {
                        mail_data.receive(pack);
                        String message = new String(pack.getData(), 0, pack.getLength());
                        in_message.append("收到的数据" + message + "\n");
                    } catch (Exception e) {

                    }
            }


        }
    }


}
