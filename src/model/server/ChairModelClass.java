package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;
import utils.R;
import vo.Chair;
import vo.ChairUseRecord;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: ChairModelClass
 * @Description: 座椅
 * @Author: ChenQ
 * @Date: 2022/1/3 on 21:59
 */
public class ChairModelClass implements ChairModel {
    @Override
    public int updateChair(Object c) {
        BaseDAO chairDao = ObjectFactory.createDaoObject("chairDao");
        return chairDao.update(c);
    }

    @Override
    public List<Object> getChairs() {
        BaseDAO chairDao = ObjectFactory.createDaoObject("chairDao");
        return chairDao.getAll();
    }

    @Override
    public int initChair(List<Object> list) {
        BaseDAO chairDao = ObjectFactory.createDaoObject("chairDao");
        for (int i=0;i<list.size();i++){
            ChairUseRecord chairUseRecord = (ChairUseRecord) list.get(i);
            Chair chairDaoByKey = (Chair) chairDao.getByKey(chairUseRecord.getChairId());
            chairDaoByKey.setStatus(R.CHAIR_AVAILABLE);
            int code = chairDao.update(chairDaoByKey);
            if (code == BaseDAO.FAILED){
                return BaseDAO.FAILED;
            }
        }
        return BaseDAO.SUCCEED;
    }

    @Override
    public Object getByKey(Object key) {
        BaseDAO chairDao = ObjectFactory.createDaoObject("chairDao");
        return chairDao.getByKey(key);
    }

    @Override
    public int cancelChair(Object key) {
        Chair chair = new Chair();
        if (key instanceof String)
            chair.setChairId(String.valueOf(key));
        else if (key instanceof Chair)
            chair.setChairId(((Chair) key).getChairId());
        else chair.setChairId("-2");
        chair.setStatus(R.CHAIR_AVAILABLE);
        return updateChair(chair);
    }

}
