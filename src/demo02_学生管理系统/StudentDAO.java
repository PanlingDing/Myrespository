package demo02_学生管理系统;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* @ProjectName ITheima_JiuYe
 * @ClassName StudentDAO
 * @Author 丁攀领
 * @Date 2018/8/29 11:54
 */
public class StudentDAO {
    //读取文件中所有学员信息
    public ArrayList<Student> readAll() {
        ArrayList<Student> list = new ArrayList<Student>();
        File file = new File("MyStubms_IO\\Student.txt");
        try (BufferedReader fileIn = new BufferedReader(new FileReader(file));
        ) {
            String str;
            while ((str = fileIn.readLine())!=null) {
                String[] strs = str.split(",");
                //将取出的字符串分割构造成Student对象  并存入集合
                Student stu = new Student(strs[0], strs[1], Integer.parseInt(strs[2]), strs[3]);
                list.add(stu);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //把集合中所有的学生信息写出到文件中
    public void writeAll(List<Student> list) {
        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter("MyStubms_IO\\Student.txt"));
        ){
            //排序后写入文件
            Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
            for (Student stu : list) {
                fileOut.write(stu.getId() + "," + stu.getName() + "," +
                        stu.getAge() + "," + stu.getSex());
                fileOut.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
