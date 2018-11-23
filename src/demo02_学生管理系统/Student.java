package demo02_学生管理系统;

import java.util.Objects;

/* @ProjectName ITheima_JiuYe
 * @ClassName Student
 * @Author 丁攀领
 * @Date 2018/8/13 20:03
 */
public class Student {
    private String name;
    private String id;
    private String sex;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(id, student.id) &&
                Objects.equals(sex, student.sex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id, sex, age);
    }

    @Override
    public String toString() {
        return "姓名:'" + name + '\'' +
                ", 学号:'" + id + '\'' +
                ", 性别:'" + sex + '\'' +
                ", 年龄:" + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String id, String name, int age, String sex) {

        this.name = name;
        this.id = id;
        this.sex = sex;
        this.age = age;
    }

    public Student() {

    }
}
