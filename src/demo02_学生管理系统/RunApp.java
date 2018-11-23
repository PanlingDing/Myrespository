package demo02_学生管理系统;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* @ProjectName ITheima_JiuYe
 * @ClassName RunApp
 * @Author 丁攀领
 * @Date 2018/8/17 10:27
 */
public class RunApp {
    public static void main(String[] args) {
//        ArrayList<Student> list = new ArrayList<Student>();
        //此处传参 直接传的是从文件中取好的集合
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> list = studentDAO.readAll();

        ArrayList<User> users = new ArrayList<User>();
        Scanner sc = new Scanner(System.in);
        //测试数据
        list.add(new Student("1","哈哈",   20,"男"));
        list.add(new Student("2","嘿嘿", 21, "男"));
        list.add(new Student("3","嘻嘻", 19, "女"));
        users.add(new User("aaa", "222222"));
        //欢迎界面
        welcom();
        while (true) {
            System.out.println("请选择您的操作:\n1.注册\t2.登陆\t3.退出系统");
            String num = sc.next();
            switch (num) {
                case "1":
                    register(users, sc);
                    break;
                case "2":
                    login(sc, users, list);
                    break;
                case "3":
                    System.out.println("谢谢使用,再见");
                    System.exit(0);
                default:
                    System.out.println("[错误指令]请输入正确的指令");
            }
        }
    }

    //欢迎
    public static void welcom() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 30; j++) {
                if (i > 0 && i < 4 && j > 0 && j < 29) { //取出第1、5行和第1、8排
                    System.out.print(" "); //打印空格
                } else {
                    System.out.print("*"); //不换行打印
                }
                if (i == 2 && j == 3) {
                    System.out.print("欢迎使用学生管理系统");
                }
            }
            System.out.println();
        }
    }

    //注册
    private static void register(ArrayList<User> users, Scanner sc) {
        System.out.println("请输入用户名(不得超过8位,不能含有非法字符)");
        loop:
        while (true) {
            //输入用户名
            String newName = sc.next();
            if (newName.length() <= 0 || newName.length() > 8) {
                System.out.println("[错误]用户名必须长度必须在1~8之间,请重新输入");
                continue;
            }
            for (int i = 0; i < newName.length(); i++) {
                char c = newName.charAt(i);
                if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9')) {
                    System.out.println("[错误]用户名中含有非法字符,只允许输入数字和字母,请重新输入");
                    continue loop;
                }
            }
            for (User user : users) {
                if (user.getName().equals(newName)) {
                    System.out.println("[错误]用户名已存在,请重新输入用户名");
                    continue loop;
                }
            }
            System.out.println("请输入密码(长度为6的数字组合)");
            String newPassword = "";
            loop2 :
            while (true) {
                //输入密码
                newPassword = sc.next();
                for (int i = 0; i < newPassword.length(); i++) {
                    char c = newPassword.charAt(i);
                    if (c < '0' || c > '9') {
                        System.out.println("[错误]密码必须是6位数字,请重新输入");
                        continue loop2;
                    }
                }
                if (!(newPassword.length() == 6)) {
                    System.out.println("[错误]密码的长度必须是6位,请重新输入");
                    continue;
                }else {
                    break loop2;
                }
            }
            System.out.println("确认注册:Y 重新注册:N");
            while (true) {
                String s = sc.next();
                if (!(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"))) {
                    System.out.println("[错误指令]请重新输入 Y / N");
                }
                if ("y".equalsIgnoreCase(s)) {
                    break;
                }
                if (s.equalsIgnoreCase("n")) {
                    System.out.println("重新注册,请重新输入用户名");
                    continue loop;
                }
            }
            //封装到对象中 并保存到uses集合里
            User user = new User(newName, newPassword);
            users.add(user);
            System.out.println("注册成功");
            System.out.println(user);
            return;
        }
    }

    //登陆
    private static void login(Scanner sc, ArrayList<User> users, ArrayList<Student> list) {
        System.out.println("请输入用户名:");
        String name = sc.next();
        System.out.println("请输入密码:");
        String password = sc.next();
        //封装到user对象
        User user1 = new User(name, password);
        if (users.contains(user1)) {
            System.out.println("登录成功\n");
            Menu.selent(list);
        } else {
            System.out.println("用户名或密码错误");
        }
    }
}
