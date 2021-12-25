package test;

import java.io.*;

public class Example12_5 {
    public static void main(String[] args) {
        byte[] b = "Hello,最近忙吗".getBytes();
        try {
            InputStream is = new FileInputStream("/Users/hehe/Desktop/1.jpeg");
            byte[] temp = new byte[1028];

            OutputStream out = null;
            int start = 0;
            int n = 0;
            while ((n = is.read(temp, 0, 102)) != -1) {
                out = new FileOutputStream(new File("2.jpeg"));

                out.write(temp, start, n);
                start += n;


            }
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
