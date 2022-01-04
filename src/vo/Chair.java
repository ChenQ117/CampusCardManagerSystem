package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: Site
 * @Description: 座位信息
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:18
 */
public class Chair {
    private String chairId;//座位编号
    private int status;//座位状态

    public String getChairId() {
        return chairId;
    }

    public void setChairId(String chairId) {
        this.chairId = chairId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setChairId(resultSet.getString("chairId"));
        setStatus(resultSet.getInt("status"));
    }
}
