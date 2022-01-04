package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: Student
 * @Description: 学生
 * @Author: ChenQ
 * @Date: 2021/12/28 on 17:59
 */
public class Student extends Card {
    private String userCardId;
    private String name;
    private String gender;//性别
    private String collage;//学院
    private String major;//系
    private int root;//选座权限

    public Student() {
        type = 2;
    }

    @Override
    public String getUserCardId() {
        return userCardId;
    }

    @Override
    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
    public void setInfo2(ResultSet resultSet) throws SQLException {
        setGender(resultSet.getString("gender"));
        setUserCardId(resultSet.getString("userCardId"));
        setName(resultSet.getString("name"));
        setMajor(resultSet.getString("major"));
        setCollage(resultSet.getString("collage"));
    }
}
