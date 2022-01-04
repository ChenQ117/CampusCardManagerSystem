package control;

import model.server.CardModel;
import model.server.ManagerModelClass;
import utils.ObjectFactory;
import vo.Card;
import vo.Student;
import vo.Teacher;

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
 * @Date: 2021/12/29 on 11:11
 */
@WebServlet("/form/createUser")
public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String path = "../";
        ManagerModelClass ManagerModel = ObjectFactory.createManagerModel();
        int code;
        if (type.equals("T")){
            Teacher card = new Teacher();
            card.setPassword(request.getParameter("password"));
            card.setRoot(Integer.valueOf(request.getParameter("root")));
            card.setMoney(Double.valueOf(request.getParameter("money")));
            code = ManagerModel.createUser(card);
        }else {
            Student card = new Student();
            card.setPassword(request.getParameter("password"));
            card.setRoot(Integer.valueOf(request.getParameter("root")));
            card.setMoney(Double.valueOf(request.getParameter("money")));
            card.setCollage(request.getParameter("collage"));
            card.setMajor(request.getParameter("major"));
            card.setName(request.getParameter("name"));
            card.setGender(request.getParameter("gender"));
            code = ManagerModel.createUser(card);
        }
        HttpSession session = request.getSession();
        if (code==CardModel.SUCCEED){
            response.sendRedirect("../index_0.jsp");
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败");
            response.sendRedirect("createForm.jsp");
            session.setAttribute("error","创建失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
