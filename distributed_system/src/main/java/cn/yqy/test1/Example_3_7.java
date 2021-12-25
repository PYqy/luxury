package cn.yqy.test1;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import static com.sun.tools.doclint.Entity.or;

public class Example_3_7 {
    public static void main(String[] args) {
        Book book = new Book("32494839", "Java面向对象的程序设计");
        Book book2 = new Book("2939034", "C++面向对象的程序设计");
        Book book3 = new Book("12993039", "C面向过程的程序设计");
        HashMap<String, Book> table = new HashMap<String, Book>();
        table.put(book.ISBN, book);
        table.put(book2.ISBN, book2);
        table.put(book3.ISBN, book3);
        String key = "2939034";
        if (table.containsKey(key)) {
            Book book4 = table.get(key);
            System.out.println(book4.name + " 有货");
        }
        int number = table.size();
        System.out.println("散列映射中有" + number + "个元素");


        for (int i = 0; i < 10000; i++) {
            table.put(i + "", new Book(i + "", "i" + i));
        }


        Collection<Book> collection = table.values();
        Iterator<Book> it = collection.iterator();
        while (it.hasNext()) {
            Book next = it.next();
            System.out.println(next.ISBN + "--" + next.name);
        }


    }
}

class Book {
    String ISBN, name;

    public Book(String ISBN, String name) {
        this.ISBN = ISBN;
        this.name = name;
    }

}
