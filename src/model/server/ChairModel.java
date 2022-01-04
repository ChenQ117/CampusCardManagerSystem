package model.server;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: ChairModel
 * @Description: 座椅信息
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:51
 */
public interface ChairModel {
    int updateChair(Object chair);//更新座位状态信息
    List<Object> getChairs();//获取座位状态信息
    int initChair(List<Object> list);//初始化座位状态信息
    Object getByKey(Object key);//根据座位编号获取座位信息
    int cancelChair(Object key);//取消选座
}
