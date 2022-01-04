package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: PhysicalTest
 * @Description: 体测项目
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:25
 */
public class PhysicalItem {
    private String itemId;//项目编号
    private String itemName;//项目名称

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setItemId(resultSet.getString("itemId"));
        setItemName(resultSet.getString("itemName"));
    }
}
