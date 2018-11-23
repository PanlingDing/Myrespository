package demo02_学生管理系统;

import java.util.Objects;

/* @ProjectName ITheima_JiuYe
 * @ClassName User
 * @Author 丁攀领
 * @Date 2018/8/17 10:47
 */
public class User {
    private String name;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password=" + password +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password,user.password) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, password);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {

        this.name = name;
        this.password = password;
    }

    public User() {

    }
}
