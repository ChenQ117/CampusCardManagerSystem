package control;

import model.server.ChairModel;
import model.server.ChairUseRecordModel;
import utils.ObjectFactory;
import vo.Card;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2022/1/4 on 0:09
 */
@WebServlet("/page/CancelChair")
public class CancelChair extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("user");
        ChairUseRecordModel chairUseRecordModel = ObjectFactory.createChairUseRecordModel();
        int code = chairUseRecordModel.cancelChair(card);
        if (code == ChairUseRecordModel.NO_CHAIR){
            session.setAttribute("info","您当前暂无选中的座位，无需取消");
        }else if (code == ChairUseRecordModel.FAILED){
            session.setAttribute("info","发生了未知的错误，取消失败");
        }else {
            session.setAttribute("info","取消成功");
        }
        response.sendRedirect("cancelChair.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
