package control;

import model.server.SchoolAffairCenterModel;
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
 * @Description: ${DESCRIPTION}
 * @Author: ChenQ
 * @Date: 2022/1/2 on 23:33
 */
@WebServlet("/page/SchoolAffairCenter")
public class SchoolAffairCenter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Card card = (Card) session.getAttribute("user");
        SchoolAffairCenterModel schoolAffairCenterModel = ObjectFactory.createSchoolAffairCenterModel();
        String query = request.getParameter("query");
        if (query.toLowerCase().equals("schoolroll")){
            Object schoolroll = schoolAffairCenterModel.getSchoolroll(card);
            if (schoolroll==null){
                session.setAttribute("error","只有学生能查看学业信息");
            }
            session.setAttribute("schoolroll",schoolroll);
        }else if (query.toLowerCase().equals("transcript")){
            List<Object> transcript = schoolAffairCenterModel.getTranscript(card);
            if (transcript==null){
                session.setAttribute("error","只有学生能查看学业信息");
            }
            session.setAttribute("transcript",transcript);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
