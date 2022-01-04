package control;

import model.server.CardModel;
import model.server.FriendModel;
import model.server.ManagerModelClass;
import utils.ObjectFactory;
import vo.Card;
import vo.Friend;

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
 * @Date: 2021/12/29 on 13:17
 */
@WebServlet("/page/DeleteUser")
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userCardId = request.getParameter("userCardId");
        ManagerModelClass managerModelClass = ObjectFactory.createManagerModel();
        int code = managerModelClass.deleteUser(userCardId);
        if (code == CardModel.FAILED){
            session.setAttribute("error","删除失败");
            response.sendRedirect("deleteUser.jsp");
            return;
        }
        response.sendRedirect("../index_0.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
