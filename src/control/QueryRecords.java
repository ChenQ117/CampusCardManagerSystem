package control;

import model.server.FinancialRecordModel;
import utils.ObjectFactory;
import vo.Card;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${查询收支记录}
 * @Author: ChenQ
 * @Date: 2021/12/30 on 14:43
 */
@WebServlet("/page/QueryRecords")
public class QueryRecords extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("user");
        FinancialRecordModel financialRecordModel = ObjectFactory.createFinancialRecordModel();
        List<Object> records = financialRecordModel.getRecords(card.getUserCardId());
        if (records == null){
            session.setAttribute("error","查询失败");
            response.sendRedirect("rechargeMenu.jsp");
            return;
        }else if (records.size()==0){
            session.setAttribute("error","暂无记录");
        }else {
            session.setAttribute("records",records);
        }
        response.sendRedirect("queryRecords.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
