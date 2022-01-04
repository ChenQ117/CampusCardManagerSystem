package control;

import model.dao.BaseDAO;
import model.server.CardModel;
import model.server.PhysicalItemModel;
import model.server.PhysicalTestModel;
import model.server.StudentModelClass;
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
 * @Date: 2022/1/3 on 15:39
 */
@WebServlet("/page/PhysicalTest")
public class PhysicalTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String cardId = request.getParameter("cardId");
        String query = request.getParameter("query");
        if ("confirm".equals(query)){//核对学生信息
            StudentModelClass studentModel = (StudentModelClass) ObjectFactory.createStudentModel();
            Object student = studentModel.getStudent(cardId);
            PhysicalItemModel physicalItemModel = ObjectFactory.createPhysicalItemModel();
            List<Object> physicalItemList = physicalItemModel.getPhysicalItemList();
            session.setAttribute("physicalList",physicalItemList);
            session.setAttribute("student",student);
        }else {//录入体测成绩
            String itemId = request.getParameter("item");
            String score = request.getParameter("score");
            String stTime = request.getParameter("stTime");
            String name = request.getParameter("name");
            vo.PhysicalTest physicalTest = new vo.PhysicalTest();
            physicalTest.setItemId(itemId);
            physicalTest.setName(name);
            physicalTest.setStGrade(score);
            physicalTest.setStTime(stTime);
            physicalTest.setUserCardId(cardId);
            PhysicalTestModel physicalTestModel = ObjectFactory.createPhysicalTestModel();
            int code = physicalTestModel.addPhysicalScore(physicalTest);
            if (code== BaseDAO.FAILED){
//                session.setAttribute("error","录入失败，请重新录入");
//                response.sendRedirect("physicalTest.jsp");
                request.setAttribute("error","录入失败，该体育项目成绩已经录入，请勿重复操作");
                request.getRequestDispatcher("physicalTest.jsp").forward(request,response);
                return;
            }
            Card user = (Card) session.getAttribute("user");
            response.sendRedirect("../index_"+user.getType()+".jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
