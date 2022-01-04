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
 * @ClassName: ChairDao
 * @Description: 座位信息
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:35
 */
public class ChairDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "chair";
    public final static String KEY = "chairId";
    public ChairDao(){
        pool = ConnectionPool.getInstance();
    }
    @Override
    public int add(Object obj) {
        return 0;
    }

    @Override
    public int update(Object obj) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                Chair chair = (Chair) obj;
                String sql = "update "+ TABLENAME +" set status=? where "+KEY+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,String.valueOf( chair.getStatus()));
                pst.setString(2,String.valueOf(chair.getChairId()));
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
                return FAILED;
            }
        }
        pool.release(connection);
        return SUCCEED;
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
                if (key instanceof String)
                    pst.setString(1,String.valueOf( key));
                else if (key instanceof Chair)
                    pst.setString(1,((Chair) key).getChairId());
                else pst.setString(1,"");
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()){
                    Chair chair = new Chair();
                    chair.setInfo(resultSet);
                    return chair;
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
                    Chair chair = new Chair();
                    chair.setInfo(resultSet);
                    list.add(chair);
                }
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
