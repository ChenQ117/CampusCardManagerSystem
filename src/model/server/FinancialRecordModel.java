package model.server;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FinancialRecordModel
 * @Description: 收支记录
 * @Author: ChenQ
 * @Date: 2021/12/29 on 15:08
 */
public interface FinancialRecordModel {
    int pay(Object financial);
    List<Object> getRecords(String cardId);
}
