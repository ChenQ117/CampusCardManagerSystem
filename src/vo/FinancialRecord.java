package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: PayFlow
 * @Description: 收支记录，流水账单
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:06
 */
public class FinancialRecord {
    private String id;
    private String cardId;//用户卡号
    private String payTime;//支付时间
    private double payMoney;//支付金额
    private String payment;//支付方式

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setCardId(resultSet.getString("cardId"));
        setPayment(resultSet.getString("payment"));
        setPayMoney(resultSet.getDouble("payMoney"));
        setPayTime(resultSet.getString("payTime"));
        setId(resultSet.getString("id"));
    }}
