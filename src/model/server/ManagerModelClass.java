package model.server;

import model.dao.BaseDAO;
import model.dao.CardDao;
import utils.ObjectFactory;
import vo.Card;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: ManagerModelClass
 * @Description: 管理员的功能
 * @Author: ChenQ
 * @Date: 2021/12/29 on 11:32
 */
public class ManagerModelClass extends CardModelClass {

    public int createUser(Object user) {
        BaseDAO cardDao = ObjectFactory.createDaoObject("cardDao");
        int code1=cardDao.add(user);
        if (((Card)user).getType()==2){
            List<Object> all = cardDao.getAll();
            Card o =(Card) all.get(all.size() - 1);
            ((Card) user).setUserCardId(o.getUserCardId());
            BaseDAO studentDao = ObjectFactory.createDaoObject("studentdao");
            int code2 = studentDao.add(user);
            if (code1==code2 &&code1==SUCCEED)
                return SUCCEED;
            else return FAILED;
        }
        return code1;
    }
    public int deleteUser(Object key){
        BaseDAO cardDao = new CardDao();
        return cardDao.delete(key);
    }
}
