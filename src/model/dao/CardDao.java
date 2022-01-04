package model.dao;

import utils.ConnectionPool;
import vo.Card;
import vo.Manager;
import vo.Student;
import vo.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao implements BaseDAO {
	private ConnectionPool pool;
	public CardDao(){
		pool = ConnectionPool.getInstance();
	}
	public final static int MANAGER =0;
	public final static int TEACHER =1;
	public final static int STUDENT =2;
	public final static String TABLENAME = "card";
	public final static String KEY = "userCardId";
	@Override
	public int add(Object obj) {
		Connection con = pool.getConnection();
		try {
			String sql = "insert into "+ TABLENAME +"(password,money,root,type) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,((Card) obj).getPassword());
			pst.setDouble(2,((Card) obj).getMoney());
			pst.setInt(3,((Card) obj).getRoot());
			pst.setInt(4,((Card) obj).getType());
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
		Connection con = pool.getConnection();
		try {
			String sql = "update "+ TABLENAME +" set money=?"+" where "+KEY+"= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1,((Card)obj).getMoney());
			pst.setString(2,((Card)obj).getUserCardId());
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
	public int delete(Object key) {
		Connection con = pool.getConnection();
		try {
			String sql = "delete from "+ TABLENAME +" where "+KEY+"= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,String.valueOf(key));
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
		if (connection!=null){
			try {
				String sql = "select * from "+ TABLENAME +" where "+KEY+" =?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,String.valueOf( key));
				ResultSet resultSet = pst.executeQuery();
				if (resultSet.next()){
					Card card = new Card();
					if (resultSet.getInt("type")==MANAGER)
						card = new Manager();
					else if (resultSet.getInt("type")== TEACHER)
						card = new Teacher();
					else if (resultSet.getInt("type")== STUDENT)
						card = new Student();
					card.setInfo(resultSet);
					pool.release(connection);
					return card;
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
				List<Object> cardList = new ArrayList<>();
				while (resultSet.next()){
					Card card = new Card();
					if (resultSet.getInt("type")==MANAGER)
						card = new Manager();
					else if (resultSet.getInt("type")== TEACHER)
						card = new Teacher();
					else if (resultSet.getInt("type")== STUDENT)
						card = new Student();
					card.setInfo(resultSet);
					cardList.add(card);
				}
				pool.release(connection);
				return cardList;
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		pool.release(connection);
		return null;
	}

	@Override
	public List<Object> getAll(String attribute, Object value) {
		List<Object> cardList = new ArrayList<Object>();
		Connection connection = pool.getConnection();
		if (connection!=null){
			try {
				String sql = "select * from "+ TABLENAME +" where ?=?";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1,attribute);
				pst.setString(2,(String) value);
				ResultSet resultSet = pst.executeQuery();
				while (resultSet.next()) {
					vo.Card card = new vo.Card();
					if (resultSet.getInt("type")==MANAGER)
						card = new Manager();
					else if (resultSet.getInt("type")== TEACHER)
						card = new Teacher();
					else if (resultSet.getInt("type")== STUDENT)
						card = new Student();
					card.setInfo(resultSet);
					cardList.add(card);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pool.release(connection);
		return cardList;
	}

	@Override
	public long getCount(String attribute, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exits(String attribute, String value) {
		// TODO Auto-generated method stub
		return false;
	}

}
