package demo02_学生管理系统;

import java.util.*;

/* @ProjectName ITheima_JiuYe
 * @ClassName Menu
 * @Author 丁攀领
 * @Date 2018/8/12 22:34
 */
public class Menu {
    /*public static void menu(ArrayList<Student> list, Scanner sc){
        //选择界面 功能
        selent(list);
    }*/
    //选项
    public static void selent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("主菜单:请选择操作:\n1.添加学生\t2.修改学生\t3.删除学生\t4.查询所有学生\t5.查找学生\t0.退出系统");
            String str = sc.next();
            switch (str) {
                case "1":
                    addStu(list, sc);
                    break;
                case "2":
                    repairStu(list, sc);
                    break;
                case "3":
                    removeStu(list, sc);
                    break;
                case "4":
                    showStu(list);
                    break;
                case "5":
                    findStu(list, sc);
                    break;
                case "0":
                    System.out.println("感谢您的使用");
                    System.exit(0);
                default:
                    System.out.println("请输入正确的选项");
            }
        }
    }

    //修改
    private static void repairStu(ArrayList<Student> list, Scanner sc) {
        System.out.println("请输入将要修改的学生ID(退出查询输入 0)");
        while (true) {
            String fixID = sc.next();
            //输入0退出修改模块
            if (fixID.equals("0")) {
                System.out.println("修改结束");
                return;
            }
            //未输入0 进入修改模块 验证学号的合法性
            for (Student stu : list) {
                String stuId = stu.getId();
                //学号合法 执行修改
                if (fixID.equals(stuId)) {
                    System.out.println("请输入新姓名(保留原值输入 0)");
                    String newName = sc.next();
                    if (!"0".equals(newName))
                        stu.setName(newName);

                    System.out.println("请输入新学号(保留原值输入 0)");
                    loop:
                    while (true) {
                        String newID = sc.next();
                        String hasID = "";
                        if (!newID.equals("0")) {
                            Student student = new Student();
                            for (int i = 0; i < list.size(); i++) {
                                student = list.get(i);
                                hasID = student.getId();
                                //学号存在 立即跳出
                                if (newID.equals(hasID)) {
                                    System.out.println("学号已存在请重新输入");
                                    continue loop;
                                }
                            }
                            if (!newID.equals(hasID)) {
                                student.setId(newID);
                                break loop;
                            }
                        } else {
                            break;
                        }
                    }

                    System.out.println("请输入新性别(保留原值输入 0)");
                    while (true) {
                        String newSex = sc.next();
                        if (!newSex.equals("0")) {
                            if (newSex.equals("男") || newSex.equals("女")) {
                                stu.setSex(newSex);
                                break;
                            } else {
                                System.out.println("请输入正确的性别(男/女)");
                                continue;
                            }
                        } else {
                            break;
                        }
                    }

                    System.out.println("请输入新年龄(保留原值输入 0)");
                    int newAge = sc.nextInt();
                    if (newAge != 0)
                        stu.setAge(newAge);

                    new StudentDAO().writeAll(list);
                    System.out.println("修改完成");
                    return;
                }
            }
            //未找到ID
            System.out.println("[错误]没有找到该学生的学号,请重输入学号(退出查询输入 0):");
            continue;
        }

    }

    //查找
    private static void findStu(ArrayList<Student> list, Scanner sc) {
        while (true) {
            System.out.println("请选择查找方式\n1.按姓名查找  2.按学号查找  3.按年龄查找  4.按性别查找  0.退出查找");
            String num = sc.next();
            switch (num) {
                case "1":
                    namefind(list, sc);
                    break;
                case "2":
                    idfind(sc, list);
                    break;
                case "3":
                    agefind(list, sc);
                    break;
                case "4":
                    sexfind(list, sc);
                    break;
                case "0":
                    System.out.println("查找结束\n");
                    return;
                default:
                    System.out.println("请输入正确的指令");
            }
        }
    }

    //按性别查找
    private static void sexfind(ArrayList<Student> list, Scanner sc) {
        ArrayList<Student> sexStu = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("请输入要查找的性别");
            String fidsex = sc.next();
            for (Student student : list) {
                if (fidsex.equals(student.getSex())) {
                    sexStu.add(student);
                }
            }
            if (sexStu.size() != 0) {
                System.out.println("性别为" + fidsex + "的学生为");
                System.out.println("===============================");
                for (Student student : sexStu) {
                    System.out.println(student);
                }
                System.out.println("===============================");
                System.out.println("查找完毕");
                return;
            }
            System.out.println("没有性别为" + fidsex + "的学生,是否继续查找 Y(是) / N(否)");
            while (true) {
                String str = sc.next();
                if (!(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n"))) {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
                if (str.equalsIgnoreCase("y")) {
                    continue loop;
                } else if ("n".equalsIgnoreCase(str)) {
                    System.out.println("退出性别查找");
                    return;
                } else {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
            }
        }
    }

    //按年龄查找
    private static void agefind(ArrayList<Student> list, Scanner sc) {
        Scanner scage = new Scanner(System.in);
        ArrayList<Student> stus = new ArrayList<>();
        loop:
        while (true) {
            int fidage = 0;
            System.out.println("请输入要查找的年龄");
            try {
                fidage = scage.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("非法输入");
                //初始化Scanner 一个Scanner对象(scage)赋值一次 当出现异常后 此Scanner对象便不可用
                scage = new Scanner(System.in);
                continue loop;
            }
            for (Student student : list) {
                if (fidage == student.getAge()) {
                    stus.add(student);
                }
            }
            if (stus.size() != 0) {
                System.out.println(fidage + "岁" + "的学生:");
                System.out.println("===============================");
                for (Student student : stus) {
                    System.out.println(student);
                }
                System.out.println("===============================");
                System.out.println("查找完毕");
                return;
            }
            System.out.println("没有" + fidage + "岁的学生" + ",是否继续查找 Y(是) / N(否)");
            while (true) {
                String str = scage.next();
                if (!(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n"))) {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
                if (str.equalsIgnoreCase("y")) {
                    continue loop;
                } else if ("n".equalsIgnoreCase(str)) {
                    System.out.println("退出年龄查找\n");
                    return;
                } else {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
            }

        }
    }

    //按姓名查找
    private static void namefind(ArrayList<Student> list, Scanner sc) {
        ArrayList<Student> stus = new ArrayList<>();
        LOOP:
        while (true) {
            System.out.println("请输入要查找的学生姓名");
            String fidname = sc.next();
            for (Student student : list) {
                if (fidname.equals(student.getName())) {
                    stus.add(student);
                }
            }
            if (stus.size() != 0) {
                System.out.println("名叫 " + fidname + " 的学生的信息:");
                System.out.println("===============================");
                for (Student student : stus) {
                    System.out.println(student);
                }
                System.out.println("===============================");
                System.out.println("查找完毕");
                return;
            }
            if (stus.size() == 0) {
                System.out.println("没有找到该学生,是否继续查找 Y(是) / N(否)");
                while (true) {
                    String str = sc.next();
                    if (!(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n"))) {
                        System.out.println("[错误]请输入正确的指令 Y/N");
                        continue;
                    }
                    if (str.equalsIgnoreCase("y")) {
                        continue LOOP;
                    } else if ("n".equalsIgnoreCase(str)) {
                        System.out.println("退出姓名查找");
                        return;
                    } else {
                        System.out.println("[错误]请输入正确的指令 Y/N");
                        continue;
                    }
                }
            }
        }
    }

    //按学号查找
    private static void idfind(Scanner sc, ArrayList<Student> list) {
        loop:
        while (true) {
            System.out.println("请输入要查找学生的学号");
            String findID = sc.next();
            for (Student stu : list) {
                String stuId = stu.getId();
                if (findID.equals(stuId)) {
                    System.out.println("================================");
                    System.out.println("该学生的信息:\n" + stu);
                    System.out.println("================================");
                    return;
                }
            }
            System.out.println("没有找到该学生,是否继续查找 Y(是) / N(否)");
            while (true) {
                String str = sc.next();
                if (!(str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n"))) {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
                if (str.equalsIgnoreCase("y")) {
                    continue loop;
                } else if ("n".equalsIgnoreCase(str)) {
                    System.out.println("退出学号查找");
                    return;
                } else {
                    System.out.println("[错误]请输入正确的指令 Y/N");
                    continue;
                }
            }
        }
    }

    //删除
    private static void removeStu(ArrayList<Student> list, Scanner sc) {
        Iterator<Student> it = list.iterator();
        while (true) {
            System.out.println("请输入要删除学员的学号");
            String stuID = sc.next();
            for (int i = 0; i < list.size(); i++) {
                Student stu = list.get(i);
                String getID = stu.getId();

                if (stuID.equals(getID)) {
                    System.out.println("您要删除的学生是:" + stu);
                    System.out.println("确定要删除该学生的信息吗: Y(是) / N(否)");
                    String str = sc.next();
                    if (str.equalsIgnoreCase("y")) {
                        list.remove(i);
                        System.out.println("删除成功");
                        return;
                    }
                    if (str.equalsIgnoreCase("n")) {
                        System.out.println("取消删除");
                        return;
                    } else {
                        System.out.println("[指令错误],退出删除");
                        return;
                    }
                }
            }
            //循环结束仍没有找到
            System.out.println("[错误]没有找到该学号");

            System.out.println("是否继续删除操作? Y(是) / N(否)");
            String str2 = sc.next();
            if (str2.equalsIgnoreCase("n")) {
                System.out.println("操作取消");
                return;
            }
            if (str2.equalsIgnoreCase("y")) {
                continue;
            } else {
                System.out.println("[错误指令],退出删除");
                return;
            }
        }
    }

    //查看
    private static void showStu(ArrayList<Student> list) {
        System.out.println("==================================");
        System.out.println("学号\t 姓名\t 性别\t 年龄");
        System.out.println("--------------------------------");
        if (list.size() == 0) {
            System.out.println("没有学生信息");
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        for (Student student : list) {
            System.out.println(student.getId() + "\t " +
                    student.getName() + "\t " +
                    student.getSex() + "\t\t " +
                    student.getAge());
        }
        System.out.println("==================================");
    }

    //添加
    private static void addStu(ArrayList<Student> list, Scanner sc) {
        String newID;
        String hasID = "";
        System.out.println("请输入学号(正整数)");
        loop:
        while (true) {
            newID = sc.next();
            //控制输入的字符串为0~9的数字
            for (int i = 0; i < newID.length(); i++) {
                if (newID.charAt(i) < '1' || newID.charAt(i) > '9') {
                    System.out.println("[错误]学号必须是正整数,请重新输入");
                    continue loop;
                }
            }
            for (Student stu : list) {
                hasID = stu.getId();
                if (newID.equals(hasID)) {
                    System.out.println("学号已存在请重新输入");
                    //遇到重复的学号立即结束跳出循环
                    //此处必须跳出 否则 hasID的值是for遍历后的之后一个元素值
                    continue loop;
                }
            }
            //如果遍历完没有相等的学号 则跳出while
            if (!newID.equals(hasID)) {
                break;
            }
        }

        System.out.println("请输入姓名");
        String name = sc.next();

        String sex = "";
        while (true) {
            System.out.println("请选择性别:\t1.男\t\t2.女");
            String num = sc.next();
            switch (num) {
                case "1":
                    sex = "男";
                    break;
                case "2":
                    sex = "女";
                    break;
                default:
                    System.out.println("请选择正确的操作");
                    continue;
            }
            if (sex.equals("男") || sex.equals("女")) {
                break;
            }
        }
        System.out.println("请输入年龄");
        Scanner scage = new Scanner(System.in);
        int age;
        while (true) {
            try {
                age = scage.nextInt();
                if (age < 1 || age > 50) {
                    System.out.println("请输入正确的年龄");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("年龄非法,请重新输入");
                //Scanner对象一旦异常 需要重新创建一个对象
                scage = new Scanner(System.in);
            }
        }
        //把属性值封装到学生对象中
        Student stu = new Student();
        stu.setId(newID);
        stu.setName(name);
        stu.setSex(sex);
        stu.setAge(age);
        //把学生对象作为元素添加到集合中
        list.add(stu);
        System.out.println("添加成功\n");
    }
}
