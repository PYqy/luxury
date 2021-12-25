import java.sql.DriverManager;

public class ClassLoaderTest2 {
    public static void main(String[] args) {
        try {
            System.out.println("------------------第一种方式----------------");
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);
            System.out.println("------------------第二种方式----------------");
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(contextClassLoader);
            System.out.println("------------------第san种方式----------------");
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            System.out.println(systemClassLoader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
