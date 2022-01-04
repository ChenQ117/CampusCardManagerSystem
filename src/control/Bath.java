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
@WebServlet("/page/Bath")
public class Bath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String status = request.getParameter("status");
        String bathMoney = request.getParameter("bathMoney");
        boolean isBathing = false;
        CardModel cardModel = ObjectFactory.createCardModel();
        Card card = (Card)session.getAttribute("user");
        if ("start".equals(status)){
            //先判余额是否足够洗澡,如果够，先扣3块钱，然后再开始洗澡
            double price = -3;
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
                    session.setAttribute("error","扣费成功");
                    isBathing = true;
                    session.setAttribute("isBathing",isBathing);
                }else {
                    session.setAttribute("error","消费失败");
                }
            }else {
                session.setAttribute("error","余额不足");
            }
            response.sendRedirect("bathConsumption.jsp");
            return;
        }else if ("end".equals(status)){//异步请求
            double price = Double.parseDouble(bathMoney);
            if (price == 0){//如果未产生消费，则不需要记录
                isBathing = false;
                session.setAttribute("isBathing",isBathing);
                return;
            }
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
                    isBathing = false;
                    session.setAttribute("error","回款成功");
                    session.setAttribute("isBathing",isBathing);
                }
            }else {
                session.setAttribute("error","回款失败");
            }
//            response.sendRedirect("consumptionMenu.jsp");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
