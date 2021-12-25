package cn.yqy.hashtable;

import java.util.Scanner;

/**
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的 id 时,
 * 要求查找到该员工的 所有信息.
 * 要求:
 * 1) 不使用数据库,,速度越快越好=>哈希表(散列)
 * 2) 添加时，保证按照 id 从低到高插入 [课后思考:如果 id 不是从低到高插入，但要求各条链表仍是从低到
 * 高，怎么解决?]
 * 3) 使用链表来实现哈希表, 该链表不带表头[即: 链表的第一个结点就存放雇员信息]
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next(); //创建 雇员

                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.finById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

class HashTab {
    private EmpLinkedList[] lists;
    private int size;


    //add
    public void add(Emp emp) {
        int empHashKey = hashcod(emp.getId());
        lists[empHashKey].add(emp);


    }

    //list
    public void list() {
        for (int i = 0; i < size; i++) {
            lists[i].list(i);
        }
    }

    //findById
    public void finById(int no) {
        int empHashKey = hashcod(no);
        Emp empByID = lists[empHashKey].findEmpByID(no);
        if (empByID != null) {
            System.out.println(empByID.getId() + " " + empByID.getName() + " " + empByID.getAddress());
        } else {
            System.out.println("not find no is " + no);
        }
    }


    public int hashcod(int no) {
        return no % size;
    }

    public HashTab(int size) {
        this.size = size;
        lists = new EmpLinkedList[size];
//        for (EmpLinkedList list : lists) {
//            list = new EmpLinkedList();
//        }
        for (int i = 0; i < size; i++) {
            lists[i] = new EmpLinkedList();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public EmpLinkedList[] getLists() {
        return lists;
    }

    public void setLists(EmpLinkedList[] lists) {
        this.lists = lists;
    }
}


class EmpLinkedList {
    private Emp headEmp;

    public boolean isEmpty() {

        return headEmp == null;
    }

    //add
    public void add(Emp emp) {
        if (isEmpty()) {
            headEmp = emp;
            return;
        }
        Emp temp = headEmp;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(emp);
                break;
            }
            temp = temp.getNext();
        }
    }

    //list
    public void list(int n) {
        if (isEmpty()) {
            System.out.println((n + 1) + " list is null!!");
            return;
        }
        Emp temp = headEmp;
        while (true) {
            System.out.println(n + " " + temp.getId() + " " + temp.getName() + " " + temp.getNext());
            if (temp.getNext() == null) {
                break;
            }

            temp = temp.getNext();
        }
    }

    //find EmpById
    public Emp findEmpByID(int no) {
        if (isEmpty()) {
            System.out.println(" list is null!!");
            return null;
        }
        Emp temp = headEmp;
        while (true) {
            if (temp.getId() == no) {
                break;
            }
            if (temp.getNext() == null) {
                temp = null;
                break;
            }
            temp = temp.getNext();

        }
        return temp;
    }

}

class Emp {
    private int id;
    private String name;
    private String address;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}