package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: Transcript
 * @Description: 成绩单
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:48
 */
public class Transcript {
    private String userCardId;//学号
    private String courseName;//课程名称
    private String academicYear;//学年
    private String semester;//学期
    private double score;//成绩
    private double credits;//学分
    private String backups;//备注

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getBackups() {
        return backups;
    }

    public void setBackups(String backups) {
        this.backups = backups;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setAcademicYear(resultSet.getString("academicYear"));
        setUserCardId(resultSet.getString("userCardId"));
        setCourseName(resultSet.getString("courseName"));
        setBackups(resultSet.getString("backups"));
        setCredits(resultSet.getDouble("credits"));
        setScore(resultSet.getInt("score"));
        setSemester(resultSet.getString("semester"));
    }
}
