package cn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;

public class yqytest {
    public static void main(String[] args) {

        File file = new File("src\\mydir");
        int b;
        byte tom[] = new byte[18];
        try {
            File f = new File("src", "ExampleFile.java");
            FileInputStream in = new FileInputStream(f);
            while ((b = in.read(tom, 0, 18)) != 1) {
                String s = new String(tom, 0, b);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class FileAccept implements FilenameFilter {
    private String extendName;

    public void setExtendName(String s) {
        extendName = "." + s;
    }

    public boolean accept(File dir, String name) {
        return name.endsWith(extendName);
    }
}
