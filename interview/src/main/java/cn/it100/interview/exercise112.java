package cn.it100.interview;

import com.sun.tools.internal.ws.wsdl.document.Output;

import java.io.*;

/**
 * 写一个程序，将 d:\java 目录下所有.java 文件复制到 d:\jad 目录，并将原来文件的扩 展名.java 改为.jad。
 * listFIles方法接受一个FileFilter对象，这个FileFilter对象就是过滤的策略对象，
 * 不同的人提供不同的FileFilter实现，即提供了不同的过滤策略
 * <p>
 * <p>
 * <p>
 * 由本题总结的思想及策略模式的解析
 * 1、得到某个目录下的所有java文件集合
 * 1.1 得到目录File srcDIr = newFile（"d:\\java"）
 * 1.2 得到目录下的所有java文件：File[] files = srcDir.listFiles(new FilenameFilter() {
 * public boolean accept(File dir, String name) {
 * return name.endsWith(".java");
 * }
 * });
 * 2.将每个文件复制到另外一个目录，并改扩展名
 * 2.1 得到文件目录，如果目标目录不存在，则创建
 * 2.2 根据源文件名得到目标文件名，注意要用正则表达式，注意.的转义
 * 2.3 根据表示目录的File和目标文件名的字符串，得到表示目标文件的File。
 * //要在硬盘中准确的创建出一个文件，需要知道文件名和文件的目录
 * 2.4
 */
public class exercise112 {

    public static void main(String[] args) throws Exception {
        File srcDir = new File("java");
        if (!srcDir.exists() && srcDir.isDirectory()) {
            throw new Exception("目录不存在");
        }
        File[] files = srcDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });

        System.out.println(files.length);

        File destDir = new File("jad");
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        for (File f : files) {
            FileInputStream fis = new FileInputStream(f);
            String destFileName = f.getName().replace("\\.java$", ".jad");
            FileOutputStream fos = new FileOutputStream(new File(destDir, destFileName));
            copy(fis, fos);
            fis.close();
            fos.close();
        }
    }

    public static void copy(InputStream is, OutputStream os) throws Exception {
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
    }

}
