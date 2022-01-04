package control;

import model.dao.BaseDAO;
import model.server.CardModel;
import model.server.CardModelClass;
import model.server.StudentModelClass;
import utils.ObjectFactory;
import vo.Card;
import vo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 14:37
 */
@WebServlet("/form/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        CardModel cardModel = ObjectFactory.createCardModel();
        Card card = new Card(username,password);
        int statusCode = cardModel.login(card);
        try {
            if (statusCode == CardModel.WRONG_PASSWORD){
                request.setAttribute("error","密码错误");
                request.getRequestDispatcher("loginForm.jsp").forward(request,response);
            }else if (statusCode == CardModel.WRONG_NAME){
                request.setAttribute("error","卡号错误");
                request.getRequestDispatcher("loginForm.jsp").forward(request,response);
            }else if (statusCode == CardModel.FAILED){
                request.setAttribute("error","未知错误");
                request.getRequestDispatcher("loginForm.jsp").forward(request,response);
            }else {
                Cookie cookie = new Cookie("username",username);
                response.addCookie(cookie);
                cookie = new Cookie("password",password);
                response.addCookie(cookie);
                card = cardModel.getByKey(username);
                if (card.getType()==2){
                    StudentModelClass studentModel = (StudentModelClass) ObjectFactory.createStudentModel();
                    card = (Student) studentModel.getStudent(card);
                }
                session.setAttribute("user",card);
                request.setAttribute("error",null);
//            request.getRequestDispatcher("../../index_2.jsp").forward(request,response);
                response.sendRedirect("../index_"+card.getType()+".jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
