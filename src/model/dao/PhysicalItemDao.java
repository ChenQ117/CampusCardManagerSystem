package model.dao;

import utils.ConnectionPool;
import vo.PhysicalItem;
import vo.SchoolRoll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: PhysicalItemDao
 * @Description: 体测项目
 * @Author: ChenQ
 * @Date: 2022/1/3 on 16:07
 */
public class PhysicalItemDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "physicalItem";
    public final static String KEY = "itemId";
    public PhysicalItemDao() {
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
                    PhysicalItem physicalItem = new PhysicalItem();
                    physicalItem.setInfo(resultSet);
                    list.add(physicalItem);
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
