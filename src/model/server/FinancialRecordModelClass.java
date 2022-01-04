package model.server;

import model.dao.BaseDAO;
import model.dao.FinancialRecordDao;
import utils.ObjectFactory;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FinancialRecordModelClass
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/12/29 on 15:15
 */
public class FinancialRecordModelClass implements FinancialRecordModel {
    @Override
    public int pay(Object financial) {
        BaseDAO financialRecordDao = ObjectFactory.createDaoObject("financialRecordDao");
        return financialRecordDao.add(financial);
    }

    @Override
    public List<Object> getRecords(String cardId) {
        BaseDAO financialRecordDao =  ObjectFactory.createDaoObject("financialRecordDao");
        return financialRecordDao.getAll("cardId",cardId);
    }

}
