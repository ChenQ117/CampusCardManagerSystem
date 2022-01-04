package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: User
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 17:37
 */
public class Card {
    protected String password;
    protected String userCardId;
    protected int root;//选座权限
    protected int type;//用户类型
    protected double money;//余额

    public Card() {
    }

    public Card( String userCardId,String password) {
        this.password = password;
        this.userCardId = userCardId;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setPassword(resultSet.getString("password"));
        setUserCardId(resultSet.getString("userCardId"));
        setMoney(resultSet.getDouble("money"));
        setRoot(resultSet.getInt("root"));
        setType(resultSet.getInt("type"));
    }
}
