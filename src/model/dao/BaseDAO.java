package model.dao;

import java.util.List;

public interface BaseDAO {
	//add、update、delete返回值：
	//成功时返回SUCCEED，失败时返回FAILED
	public static final int SUCCEED=1;
	public static final int FAILED=2;
	public int add(Object obj) ;
	public int update(Object obj);
	public int delete(Object key);
	public Object getByKey(Object key);
	public List<Object> getAll();
	public List<Object> getAll(String attribute, Object value);
	public long getCount(String attribute, String value);
	public boolean exits(String attribute, String value);
}
