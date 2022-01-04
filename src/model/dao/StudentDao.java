package model.dao;

import utils.ConnectionPool;
import vo.Card;
import vo.Manager;
import vo.Student;
import vo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: StudentDao
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/12/29 on 11:44
 */
public class StudentDao implements BaseDAO {
    private ConnectionPool pool;
    public static final String TABLENAME = "student";
    public final static String KEY = "userCardId";
    public StudentDao() {
        this.pool = ConnectionPool.getInstance();
    }

    @Override
    public int add(Object obj) {
        Connection con = pool.getConnection();
        try {
            String sql = "insert into "+TABLENAME+" values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            Student student = (Student) obj;
            pst.setString(1,student.getUserCardId());
            pst.setString(2,(student.getName()));
            pst.setString(3,student.getGender());
            pst.setString(4,student.getCollage());
            pst.setString(5,student.getMajor());
            pst.setInt(6,student.getType());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return FAILED;
        }
        pool.release(con);
        return SUCCEED;
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object key) {
        return 0;
    }

    @Override
    public Object getByKey(Object user) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                if (user instanceof Student){
                    Student student = (Student) user;
                    String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,String.valueOf(((Student) user).getUserCardId() ));
                    ResultSet resultSet = pst.executeQuery();
                    if (resultSet.next()){
                        student.setInfo2(resultSet);
                        return student;
                    }
                }else {
                    String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,String.valueOf(user));
                    ResultSet resultSet = pst.executeQuery();
                    if (resultSet.next()){
                        Student student = new Student();
                        student.setInfo2(resultSet);
                        pool.release(connection);
                        return student;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        pool.release(connection);
        return null;
    }

    @Override
    public List<Object> getAll() {
        return null;
    }

    @Override
    public List<Object> getAll(String attribute, Object value) {
        return null;
    }

    @Override
    public long getCount(String attribute, String value) {
        return 0;
    }

    @Override
    public boolean exits(String attribute, String value) {
        return false;
    }
}
