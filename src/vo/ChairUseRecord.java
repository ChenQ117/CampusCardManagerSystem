package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: ChairUseRecord
 * @Description: 选座记录
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:20
 */
public class ChairUseRecord {
    private String chairId;//座位编号
    private String cardId;//用户卡号
    private String startTime;//开始使用时间
    private String endTime;//预计结束时间

    public String getChairId() {
        return chairId;
    }

    public void setChairId(String chairId) {
        this.chairId = chairId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setCardId(resultSet.getString("cardId"));
        setChairId(resultSet.getString("chairId"));
        setStartTime(resultSet.getString("startTime"));
        setEndTime(resultSet.getString("endTime"));
    }
}
