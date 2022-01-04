package model.dao;

import utils.ConnectionPool;
import vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ChairUseRecordDao
 * @Description: 选座记录
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:04
 */
public class ChairUseRecordDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "chairUseRecord";
    public final static String KEY = "cardId";
    public ChairUseRecordDao(){
        pool = ConnectionPool.getInstance();
    }
    @Override
    public int add(Object obj) {
        Connection con = pool.getConnection();
        try {
            ChairUseRecord chairUseRecord = (ChairUseRecord) obj;
            String sql = "insert into "+ TABLENAME +"(chairId,cardId,startTime,endTime) values(?,?,?,?)";
            System.out.println(con+"31");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,chairUseRecord.getChairId());
            pst.setString(2,chairUseRecord.getCardId());
            pst.setString(3,chairUseRecord.getStartTime());
            pst.setString(4,chairUseRecord.getEndTime());
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
        Connection con = pool.getConnection();
        try {
            String sql = "delete from "+ TABLENAME +" where "+KEY+" = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            if (key instanceof String)
                pst.setString(1,String.valueOf(key));
            else if (key instanceof ChairUseRecord)
                pst.setString(1,((ChairUseRecord) key).getCardId());
            else pst.setString(1,"");
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
    public Object getByKey(Object key) {
        Connection connection = pool.getConnection();
        System.out.println("74:chairrecordCon"+connection);
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,String.valueOf( key));
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()){
                    ChairUseRecord chairUseRecord = new ChairUseRecord();
                    chairUseRecord.setInfo(resultSet);
                    pool.release(connection);
                    return chairUseRecord;
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
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+attribute+" < '"+value+"'";
                System.out.println(sql+"-------103");
                PreparedStatement pst = connection.prepareStatement(sql);
                System.out.println(new Timestamp(new Date().getTime())+"--------------102");
//                pst.setTimestamp(1, new Timestamp(new Date().getTime()));
                ResultSet resultSet = pst.executeQuery();
                List<Object> list = new ArrayList<>();
                while (resultSet.next()){
                    ChairUseRecord chairUseRecord = new ChairUseRecord();
                    chairUseRecord.setInfo(resultSet);
                    list.add(chairUseRecord);
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
