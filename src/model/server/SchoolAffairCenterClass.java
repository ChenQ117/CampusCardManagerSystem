package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;
import vo.Card;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: SchoolrollInfoModelClass
 * @Description:
 * @Author: ChenQ
 * @Date: 2022/1/2 on 23:30
 */
public class SchoolAffairCenterClass implements SchoolAffairCenterModel {
    @Override
    public Object getSchoolroll(Object user) {
        Card card = (Card) user;
        if (card.getType()==2){//只有学生能查询学籍
            BaseDAO dao = ObjectFactory.createDaoObject("schoolrollInfoDao");
            return dao.getByKey(card.getUserCardId());
        }
        return null;
    }

    @Override
    public List<Object> getTranscript(Object user) {
        Card card = (Card) user;
        if (card.getType()==2){//只有学生能查询成绩
            BaseDAO dao = ObjectFactory.createDaoObject("transcriptDao");
            return dao.getAll("userCardId",card.getUserCardId());
        }
        return null;
    }

}
