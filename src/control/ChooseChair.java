package control;

import model.server.ChairModel;
import model.server.ChairUseRecordModel;
import utils.ObjectFactory;
import utils.R;
import vo.Card;
import vo.Chair;

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
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2022/1/3 on 20:03
 */
@WebServlet("/page/ChooseChair")
public class ChooseChair extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("user");
        ChairUseRecordModel chairUseRecordModel = ObjectFactory.createChairUseRecordModel();
        chairUseRecordModel.updateChair();
        ChairModel chairModel = ObjectFactory.createChairModel();
        int bookChairId = chairUseRecordModel.checkBooking(card);
        if (bookChairId==ChairUseRecordModel.NO_CHAIR){
            String chairId = request.getParameter("chooseChairId");
            if (chairId==null||"".equals(chairId)){//还未开始选座，显示可用座位列表
                List<Object> chairs = chairModel.getChairs();
                session.setAttribute("chairList",chairs);
                response.sendRedirect("chooseChair.jsp");
                return;
            }
            Object chairByKey = chairModel.getByKey(chairId);
            if (chairByKey==null){
                request.setAttribute("error","请输入合法的座位编号，该座位不存在");
                request.getRequestDispatcher("chooseChair.jsp").forward(request,response);
                return;
            }else {
                Chair chair = (Chair) chairByKey;
                if (chair.getStatus()==R.CHAIR_CHECKED){
                    request.setAttribute("error","该座位被占用，请重新选座");
                    request.getRequestDispatcher("chooseChair.jsp").forward(request,response);
                    return;
                }
                int hour = Integer.valueOf(request.getParameter("hour"));
                int code = chairUseRecordModel.chooseChair(card, chair, hour);
                if (code == ChairUseRecordModel.NO_ROOT){
                    request.setAttribute("error","您暂无选座权限");
                    request.getRequestDispatcher("chooseChair.jsp").forward(request,response);
                    return;
                }else {
                    chair.setStatus(R.CHAIR_CHECKED);
                    chairModel.updateChair(chair);
                    List<Object> chairs = chairModel.getChairs();
                    session.setAttribute("chairList",chairs);
                    request.setAttribute("seatInfo","选座成功，座位编号为："+chair.getChairId());
                    request.getRequestDispatcher("chooseChair.jsp").forward(request,response);
                    return;
                }
            }
        }else {//选中座位
            request.setAttribute("seatInfo","您已选中座位，座位编号为："+bookChairId);
            request.getRequestDispatcher("chooseChair.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
