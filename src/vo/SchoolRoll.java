package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: SchoolRegistry
 * @Description: 学籍信息
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:30
 */
public class SchoolRoll {
    private String userCardId;//学号
    private String name;//用户名
    private String collage;//学院
    private String major;//系
    private String grade;//年级
    private String adDate;//入学时间
    private String educational;//学制
    private String gradDate;//预计毕业时间
    private String userId;//身份证号
    private String studyMode;//修读方式
    private String qualification;//学历
    private String gender;//性别

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAdDate() {
        return adDate;
    }

    public void setAdDate(String adDate) {
        this.adDate = adDate;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getGradDate() {
        return gradDate;
    }

    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setInfo(ResultSet resultSet) throws SQLException {
        setAdDate(resultSet.getString("adDate"));
        setUserCardId(resultSet.getString("userCardId"));
        setCollage(resultSet.getString("collage"));
        setEducational(resultSet.getString("educational"));
        setGradDate(resultSet.getString("gradDate"));
        setMajor(resultSet.getString("major"));
        setGrade(resultSet.getString("grade"));
        setName(resultSet.getString("name"));
        setQualification(resultSet.getString("qualification"));
        setStudyMode(resultSet.getString("studyMode"));
        setUserId(resultSet.getString("userId"));
        setGender(resultSet.getString("gender"));
    }
}
