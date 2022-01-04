package model.server;

/**
 * @version v1.0
 * @ClassName: ChairUseRecordModel
 * @Description:
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:17
 */
public interface ChairUseRecordModel {
    public final static int NO_ROOT = 3;//没有权限
    public final static int NOT_AVALIABLE = 4;//座位不可选
    public static final int SUCCEED=1;
    public static final int FAILED=2;
    public static final int NO_CHAIR=-1;//没有选中的座位
    int chooseChair(Object user,Object chair,int hour);//选座
    int cancelChair(Object user);//取消选座
    int updateChair();//更新座位信息，将已经到时间的座位设置为空闲
    int checkBooking(Object object);//验证预约
}
