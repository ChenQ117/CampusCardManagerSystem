package model.server;

import model.dao.BaseDAO;
import utils.ObjectFactory;
import vo.Card;

/**
 * @version v1.0
 * @ClassName: StudentModelClass
 * @Description: 学生功能
 * @Author: ChenQ
 * @Date: 2021/12/29 on 11:41
 */
public class StudentModelClass extends CardModelClass {
    public Object getStudent(Object user){
        BaseDAO studentDao = ObjectFactory.createDaoObject("studentDao");
        return studentDao.getByKey(user);
    }
}
