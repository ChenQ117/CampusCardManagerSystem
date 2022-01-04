package model.dao;

import utils.ConnectionPool;
import vo.SchoolRoll;
import vo.Transcript;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: TranscriptDao
 * @Description:
 * @Author: ChenQ
 * @Date: 2022/1/3 on 11:32
 */
public class TranscriptDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "transcript";
    public final static String KEY_1 = "userCardId";
    public final static String KEY_2 = "courseName";
    public TranscriptDao() {
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
        return null;
    }

    @Override
    public List<Object> getAll(String attribute, Object value) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+attribute+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,(String) value);
                ResultSet resultSet = pst.executeQuery();
                List<Object> list = new ArrayList<>();
                while (resultSet.next()){
                    Transcript transcript = new Transcript();
                    transcript.setInfo(resultSet);
                    list.add(transcript);
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
    public long getCount(String attribute, String value) {
        return 0;
    }

    @Override
    public boolean exits(String attribute, String value) {
        return false;
    }
}
