package model.dao;

import utils.ConnectionPool;
import vo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: PayFlowDao
 * @Description: 收支记录
 * @Author: ChenQ
 * @Date: 2021/12/29 on 14:33
 */
public class FinancialRecordDao implements BaseDAO {
    private ConnectionPool pool;
    public final static String TABLENAME = "financialRecord";
    public final static String KEY = "id";
    public final static String SECONDARY_KEY = "cardId";

    public FinancialRecordDao() {
        this.pool = ConnectionPool.getInstance();
    }

    @Override
    public int add(Object obj) {
        Connection con = pool.getConnection();
        try {
            String sql = "insert into "+ TABLENAME +"(cardId,payTime,payMoney,payment) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,((FinancialRecord) obj).getCardId());
            pst.setString(2,((FinancialRecord) obj).getPayTime());
            pst.setDouble(3,((FinancialRecord) obj).getPayMoney());
            pst.setString(4,((FinancialRecord) obj).getPayment());
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
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,String.valueOf( key));
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()){
                    FinancialRecord financialRecord = new FinancialRecord();
                    financialRecord.setInfo(resultSet);
                    pool.release(connection);
                    return financialRecord;
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
                List<Object> financialRecordList = new ArrayList<>();
                while (resultSet.next()){
                    FinancialRecord financialRecord = new FinancialRecord();
                    financialRecord.setInfo(resultSet);
                    financialRecordList.add(financialRecord);
                }
                pool.release(connection);
                return financialRecordList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        pool.release(connection);
        return null;
    }

    @Override
    public List<Object> getAll(String attribute, Object value) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from "+ TABLENAME +" where "+attribute+" =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,String.valueOf( value));
                ResultSet resultSet = pst.executeQuery();
                List<Object> financialRecordList = new ArrayList<>();
                while (resultSet.next()){
                    FinancialRecord financialRecord = new FinancialRecord();
                    financialRecord.setInfo(resultSet);
                    financialRecordList.add(financialRecord);
                }
                pool.release(connection);
                return financialRecordList;
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
