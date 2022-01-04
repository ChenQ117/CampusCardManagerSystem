package vo;

/**
 * @version v1.0
 * @ClassName: PhysicalTest
 * @Description: 体侧记录信息
 * @Author: ChenQ
 * @Date: 2021/12/28 on 18:27
 */
public class PhysicalTest {
    private String itemId;//项目编号
    private String userCardId;//用户卡号
    private String name;//用户名
    private String stGrade;//体测成绩
    private String stTime;//体测时间

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStGrade() {
        return stGrade;
    }

    public void setStGrade(String stGrade) {
        this.stGrade = stGrade;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }
}
