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
 * @Date: 2021/12/29 on 14:28
 */
@WebServlet("/page/Recharge")
public class Recharge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("user");
        double money = Double.valueOf(request.getParameter("money"));
        String payment = request.getParameter("payment");
        CardModel cardModel = ObjectFactory.createCardModel();
        int code = cardModel.pay(card, money);
        if (code == CardModel.SUCCEED){
            FinancialRecord financialRecord = new FinancialRecord();
            financialRecord.setPayment(payment);
            financialRecord.setPayMoney(money);
            financialRecord.setCardId(card.getUserCardId());
            financialRecord.setPayTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                    .format(new Date()));
            FinancialRecordModel financialRecordModel = ObjectFactory.createFinancialRecordModel();
            int code2 = financialRecordModel.pay(financialRecord);
            if (code2 == BaseDAO.SUCCEED){
                session.setAttribute("error","充值成功");
                response.sendRedirect("rechargeMenu.jsp");
                return;
            }else {
                session.setAttribute("error","充值失败");
            }
        }else {
            session.setAttribute("error","充值失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
