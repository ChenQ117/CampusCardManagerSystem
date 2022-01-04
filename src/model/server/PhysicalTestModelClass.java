package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;

/**
 * @version v1.0
 * @ClassName: PhysicalTestModelClass
 * @Description: 录入体测成绩
 * @Author: ChenQ
 * @Date: 2022/1/3 on 16:31
 */
public class PhysicalTestModelClass implements PhysicalTestModel {
    @Override
    public int addPhysicalScore(Object o) {
        BaseDAO physicalTestDao = ObjectFactory.createDaoObject("physicalTestDao");
        return physicalTestDao.add(o);
    }
}
