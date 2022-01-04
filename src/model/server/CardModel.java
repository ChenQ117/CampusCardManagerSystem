package model.server;

import vo.Card;

public interface CardModel {
	//登录、注册、修改密码成功时返回SUCCEED
		public static final int SUCCEED=1;
	//登录、注册、修改密码失败时返回FAILED
		public static final int FAILED=2;
	//登录失败时也可返回WRONG_NAME或WRONG_PASSWORD
		public static final int WRONG_NAME=3;
		public static final int WRONG_PASSWORD=4;

		int login(Object user);
		int register(Object user);
		int changePassword(String name, String oldPassword,
                           String newPassword);
	    Card getByKey(Object key);
	    int pay(Object user,Object paymoney);//充值
	    int shopping(Object user,Object woods);//购物
}

