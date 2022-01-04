package control;

import model.server.CardModel;
import utils.ObjectFactory;
import vo.Card;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version v1.0
 * @ClassName: ${NAME}
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2021/11/13 on 14:37
 */
@WebServlet("/form/Register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        try {
            //1、账号密码是否有效
            if ("".equals(username)||"".equals(password)){
                request.setAttribute("error","账号密码不能为空!!!");
                request.getRequestDispatcher("registerForm.jsp").forward(request,response);
                return;
            }
            //2、验证两次密码是否一致
            if (!password.equals(passwordConfirm)){
                request.setAttribute("error","两次密码不一样!!!");
                request.getRequestDispatcher("registerForm.jsp").forward(request,response);
            }
            //3、注册，返回是否注册成功
            CardModel cardModel = ObjectFactory.createCardModel();
            Card card = new Card(username,password);
            int isSuccess = cardModel.register(card);
            if (isSuccess==CardModel.SUCCEED){
                request.setAttribute("registerInfo","注册成功,请完成登录");
                request.getRequestDispatcher("loginForm.jsp").forward(request,response);
                return;
            }else {
                request.setAttribute("error","该用户已经存在");
                request.getRequestDispatcher("registerForm.jsp").forward(request,response);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request, response);
    }
}
