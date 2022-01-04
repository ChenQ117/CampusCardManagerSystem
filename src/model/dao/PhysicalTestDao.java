package model.dao;

import utils.ConnectionPool;
import vo.Card;
import vo.PhysicalTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: PhysicalTestDao
 * @Description: 体测成绩
 * @Author: ChenQ
 * @Date: 2022/1/3 on 16:23
 */
public class PhysicalTestDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "physicalTest";
    public final static String KEY_1 = "itemId";
    public final static String KEY_2 = "userCardId";
    public PhysicalTestDao() {
        this.pool = ConnectionPool.getInstance();
    }
    @Override
    public int add(Object obj) {
        Connection con = pool.getConnection();
        try {
            PhysicalTest physicalTest = (PhysicalTest) obj;
            String sql = "insert into "+ TABLENAME +"(itemId,userCardId,name,stGrade,stTime) values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,physicalTest.getItemId());
            pst.setString(2,physicalTest.getUserCardId());
            pst.setString(3,physicalTest.getName());
            pst.setString(4,physicalTest.getStGrade());
            pst.setString(5,physicalTest.getStTime());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            pool.release(con);
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
    public Object getByKey(Object key) {
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
