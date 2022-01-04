package model.dao;

import utils.ConnectionPool;
import vo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: SchoolRollInfoDao
 * @Description: 学籍信息
 * @Author: ChenQ
 * @Date: 2022/1/2 on 23:16
 */
public class SchoolRollInfoDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "schoolrollInfo";
    public final static String KEY = "userCardId";
    public SchoolRollInfoDao() {
        this.pool = ConnectionPool.getInstance();
    }

    @Override
    public int add(Object obj) {
        return 0;
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
    public Object getByKey(Object key) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,String.valueOf( key));
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()){
                    SchoolRoll schoolRollInfo = new SchoolRoll();
                    schoolRollInfo.setInfo(resultSet);
                    pool.release(connection);
                    return schoolRollInfo;
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
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME;
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet resultSet = pst.executeQuery();
                List<Object> list = new ArrayList<>();
                while (resultSet.next()){
                    SchoolRoll schoolRollInfo = new SchoolRoll();
                    schoolRollInfo.setInfo(resultSet);
                    list.add(schoolRollInfo);
                }
                pool.release(connection);
                return list;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        pool.release(connection);
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
