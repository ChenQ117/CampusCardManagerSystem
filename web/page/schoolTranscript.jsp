<%@ page import="vo.Transcript" %>
<%@ page import="java.util.List" %>
<%@ page import="vo.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩</title>
</head>
<%
    Student user = (Student) session.getAttribute("user");
%>
<body>
    <center>
        <div style="width: 80%;position: center">
            <h1>黑龙江大学学生历年学习成绩表</h1>
            <hr color="black">
            <hr color="gray">
            <table>
                <tr>
                    <td><label>学号：</label></td>
                    <td><%=user.getUserCardId()%></td>
                    <td><label>姓名：</label></td>
                    <td><%=user.getName()%></td>
                    <td><label>性别：</label></td>
                    <td><%=user.getGender()%></td>
                    <td><label>学院：</label></td>
                    <td><%=user.getCollage()%></td>
                    <td><label>专业：</label></td>
                    <td><%=user.getMajor()%></td>
                </tr>
            </table>
        </div>
        <br>
        <table width="80%" >
            <%--        表头行--%>
            <tr>
                <th>学年</th>
                <th>学期</th>
                <th>课程名称</th>
                <th>成绩</th>
                <th>学分</th>
                <th>备注</th>
            </tr>
            <%
                if (session.getAttribute("pageCount")==null){
                    session.setAttribute("pageCount",10);
                }
                int pageCount = (int)session.getAttribute("pageCount");
                int perPage;
                if (request.getParameter("page")==null){
                    if (session.getAttribute("perPage")==null){
                        perPage = 1;
                    }else {
                        perPage = (int)session.getAttribute("perPage");
                    }
                }else {
                    perPage = Integer.valueOf(request.getParameter("page"));
                }
                List<Transcript> records =(List<Transcript>) session.getAttribute("transcript");
                int lastPage = (records==null||records.size()==0)?1: (int)Math.ceil(records.size()/Double.valueOf(pageCount));
                perPage = perPage>lastPage?lastPage:perPage;
                session.setAttribute("perPage",perPage);
                int k = 0;
                for(int i=0;i<pageCount;i++){
                    k=i+(perPage-1)*pageCount;
                    if (k<records.size()){
                        Transcript record = records.get(k);
                        out.print("<tr align=center>");
                        out.print("<td><label name=academicYear>"+record.getAcademicYear()+"</label></td>");
                        out.print("<td><label name=semester>"+record.getSemester()+"</label></td>");
                        out.print("<td><label name=courseName>"+record.getCourseName()+"</label></td>");
                        out.print("<td><label name=score>"+record.getScore()+"</label></td>");
                        out.print("<td><label name=credits>"+record.getCredits()+"</label></td>");
                        out.print("<td><label name=backups>"+record.getBackups()+"</label></td>");
                        out.print("</tr>");
                    }else {
                        break;
                    }
                }
                session.setAttribute("perCount",k+1);
            %>
        </table>

        <a href="schoolTranscript.jsp?page=1">|<<1</a>
        <a href="schoolTranscript.jsp?page=<%=(perPage-1)<1?1:(perPage-1)%>"><<</a>
        <%
            for (int i=0;i<4;i++){
                int index = perPage+i;
                if (index<=lastPage&&index>0){
                    out.println("<a href=schoolTranscript.jsp?page="+index+">"+index+"</a>");
                }else {
                    break;
                }
            }
            if (perPage!=lastPage && lastPage>4){
                out.println("...");
            }
        %>
        <a href="schoolTranscript.jsp?page=<%=(perPage+1)>lastPage?lastPage:(perPage+1)%>">>></a>
        <a href="schoolTranscript.jsp?page=<%=lastPage%>"><%=lastPage==0?1:lastPage%>>>|</a>
        <br>
        <a href="schoolMenu.jsp">返回学业信息菜单</a>
        <button onclick="window.print()">打印</button>
    </center>
</body>
</html>
