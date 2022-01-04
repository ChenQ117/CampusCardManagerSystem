package model.server;

import model.dao.BaseDAO;
import model.dao.ChairUseRecordDao;
import utils.ObjectFactory;
import utils.R;
import vo.Card;
import vo.Chair;
import vo.ChairUseRecord;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ChairUseRecordModelClass
 * @Description: 选座记录
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:19
 */
public class ChairUseRecordModelClass implements ChairUseRecordModel {
    @Override
    public int chooseChair(Object user, Object c,int hour) {
        Card card = (Card) user;
        Chair chair = (Chair)c;
        if (card.getRoot()!=1){
            return NO_ROOT;
        }
        if (chair.getStatus()==R.CHAIR_AVAILABLE){
            ChairUseRecord chairUseRecord = new ChairUseRecord();
            chairUseRecord.setChairId(chair.getChairId());
            chairUseRecord.setCardId(card.getUserCardId());
            Date date = new Date();
            chairUseRecord.setStartTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR,hour);
            chairUseRecord.setEndTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(calendar.getTime()));
            ChairUseRecordDao chairUseRecordDao = (ChairUseRecordDao) ObjectFactory.createDaoObject("chairUseRecordDao");
            return chairUseRecordDao.add(chairUseRecord);
        }
        return NOT_AVALIABLE;
    }

    @Override
    public int cancelChair(Object user) {
        Card card = (Card) user;
        BaseDAO chairUseRecordDao = ObjectFactory.createDaoObject("chairUseRecordDao");
        Object recordDaoByKey = chairUseRecordDao.getByKey(card.getUserCardId());
        if (recordDaoByKey == null){//说明尚未选座
            return NO_CHAIR;
        }
        ChairUseRecord chairUseRecord = (ChairUseRecord) recordDaoByKey;
        ChairModel chairModel = ObjectFactory.createChairModel();
        chairModel.cancelChair(chairUseRecord.getChairId());
        return chairUseRecordDao.delete(recordDaoByKey);
    }

    @Override
    public int updateChair() {
        ChairUseRecordDao chairUseRecordDao = (ChairUseRecordDao) ObjectFactory.createDaoObject("chairUseRecordDao");
        String s = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
//        String nousedate = new Timestamp(new Date().getTime());
        List<Object> list = chairUseRecordDao.getAll("endTime", s);
        if (list!=null){
            for (int i=0;i<list.size();i++){
                chairUseRecordDao.delete(((ChairUseRecord)list.get(i)).getCardId());
            }
            ChairModel chairModel = ObjectFactory.createChairModel();
            return chairModel.initChair(list);
        }

        return BaseDAO.SUCCEED;
    }

    @Override
    public int checkBooking(Object object) {//chairuserecodedao  chairUseRecordDao
        ChairUseRecordDao chairUseRecordDao = (ChairUseRecordDao) ObjectFactory.createDaoObject("chairUseRecordDao");
        Card card = (Card) object;
        Object byKey = chairUseRecordDao.getByKey(card.getUserCardId());
        if (byKey==null){
            return NO_CHAIR;
        }
        ChairUseRecord chairUseRecord = (ChairUseRecord) byKey;
        ChairModel chairModel = ObjectFactory.createChairModel();
        Chair chair = new Chair();
        chair.setChairId(chairUseRecord.getChairId());
        chair.setStatus(R.CHAIR_CHECKED);
        chairModel.updateChair(chair);
        return Integer.valueOf(chairUseRecord.getChairId());
    }


}
