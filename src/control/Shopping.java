package control;

import model.dao.BaseDAO;
import model.server.CardModel;
import model.server.FinancialRecordModel;
import utils.ObjectFactory;
import vo.Card;
import vo.FinancialRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/12/30 on 16:01
 */
@WebServlet("/page/Shopping")
public class Shopping extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String woods = request.getParameter("woods");
        double price = 0-Double.parseDouble(woods);
        CardModel cardModel = ObjectFactory.createCardModel();
        Card card = (Card) session.getAttribute("user");
        int code = cardModel.pay(card, price);
        if (code == CardModel.SUCCEED){
            FinancialRecord financialRecord = new FinancialRecord();
            financialRecord.setPayment("校园卡");
            financialRecord.setPayMoney(price);
            financialRecord.setCardId(card.getUserCardId());
            financialRecord.setPayTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                    .format(new Date()));
            FinancialRecordModel financialRecordModel = ObjectFactory.createFinancialRecordModel();
            int code2 = financialRecordModel.pay(financialRecord);
            if (code2 == BaseDAO.SUCCEED){
                session.setAttribute("error","消费成功");
                response.sendRedirect("consumptionMenu.jsp");
                return;
            }else {
                session.setAttribute("error","消费失败");
            }
        }else {
            session.setAttribute("error","余额不足");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
