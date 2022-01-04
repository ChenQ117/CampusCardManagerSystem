package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: PhysicalItemModelClass
 * @Description: 体测项目
 * @Author: ChenQ
 * @Date: 2022/1/3 on 16:13
 */
public class PhysicalItemModelClass implements PhysicalItemModel {
    @Override
    public List<Object> getPhysicalItemList() {
        BaseDAO physicalItemDao = ObjectFactory.createDaoObject("physicalItemDao");
        return physicalItemDao.getAll();
    }
}
