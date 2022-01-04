package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;
import vo.Card;

import java.util.List;

public class CardModelClass implements CardModel {

	@Override
	public int login(Object user) {
		BaseDAO DAO = ObjectFactory.createDaoObject("CardDao");
		List<Object> list =DAO.getAll();
		if(list.size()<1) {
			return CardModel.FAILED;
		} else {
			for(Object o:list) {
				System.out.println(((Card)o).getUserCardId());
				System.out.println(((Card)user).getUserCardId());
				if(((Card)o).getUserCardId().equals(((Card)user).getUserCardId())){
					if(((Card)o).getPassword().equals(((Card)user).getPassword())) {
						return CardModel.SUCCEED;
					}else {
						return CardModel.WRONG_PASSWORD;
					}
				}
			}
			return CardModel.WRONG_NAME;
		}
	}

	@Override
	public int register(Object user) {
		BaseDAO DAO = ObjectFactory.createDaoObject("CardDao");
		int msg = DAO.add(user);
		if(msg!=1) {
			return CardModel.FAILED;
		}
		return CardModel.SUCCEED;
	}

	@Override
	public int changePassword(String name, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Card getByKey(Object key) {
		BaseDAO DAO = ObjectFactory.createDaoObject("CardDao");
		Card card = (Card) DAO.getByKey(key);
		return card;
	}

	@Override
	public int pay(Object user, Object paymoney) {
		double money = (Double) paymoney;
		Card card = (Card) user;
		BaseDAO carddao = ObjectFactory.createDaoObject("carddao");
		if (money>0){//充值
			card.setMoney(card.getMoney()+money);
			return carddao.update(user);
		}else if (money<=0&& card.getMoney()>=Math.abs(money)){//消费
			card.setMoney(card.getMoney()+money);
			return carddao.update(user);
		}
		return FAILED;
	}

	@Override
	public int shopping(Object user, Object woods) {
		double price = Double.parseDouble((String)woods);
		Card card = (Card) user;
		if (card.getMoney()>=price){
			card.setMoney(card.getMoney());
		}
		return FAILED;
	}

}
