package test.cn.yqy;

import java.io.*;

public class fileinput {
    public static void main(String[] args) {
        File file = new File("student.docx");
        //write
        String content[] = {"天气预报", "北京晴", "上海多云", "有小雨", "大连情"};
        try {
            FileWriter write = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(write);
            for (String temp : content) {

                bw.write(temp);
                bw.newLine();//加了个回车符
            }
            bw.close();
            write.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
            br.close();
            reader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
