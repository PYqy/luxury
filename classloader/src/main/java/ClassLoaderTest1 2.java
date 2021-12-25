import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;
import java.util.Currency;

public class ClassLoaderTest1 {
    public static void main(String[] args) {
        //获取BootstrapClassloder能够加载的API路径
        System.out.println("-------------启动类加载器------------");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs
                ) {
            System.out.println(url.toExternalForm());

        }
        //从上面的路径中任意选择一个类，来看看他的类加载器是什么：引导类加载qi
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("-------------扩展类加载器------------");
        String property = System.getProperty("java.ext.dirs");
        for (String path : property.split(";")
                ) {
            System.out.println(path);

        }

        //从上面的路径中任意选择一个类，来看看他的类加载器是什么：扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);
        ClassLoader classLoader2 = ClassLoaderTest1.class.getClassLoader();
        System.out.println(classLoader2);
    }
}
