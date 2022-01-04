<%@ page import="java.util.List" %>
<%@ page import="vo.FinancialRecord" %>
<%@ page import="control.SchoolAffairCenter" %>
<%@ page import="vo.SchoolRoll" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>查询学籍</title>
</head>
<body>

<h3>学籍信息：</h3>
<center>
    <form action="#" name="myForm">
        <table width="50%">
            <%
                if (session.getAttribute("schoolroll")!=null){
                    SchoolRoll schoolroll = (SchoolRoll) session.getAttribute("schoolroll");
                    out.print("<tr>");
                    out.print("<td align=right width='50%'><label>学号：</label></td>");
                    out.print("<td><label name=userCardId>"+schoolroll.getUserCardId()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>姓名：</label></td>");
                    out.print("<td><label name=name>"+schoolroll.getName()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>性别：</label></td>");
                    out.print("<td><label name=gender>"+schoolroll.getGender()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>学院：</label></td>");
                    out.print("<td><label name=collage>"+schoolroll.getCollage()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>系：</label></td>");
                    out.print("<td><label name=major>"+schoolroll.getMajor()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>年级：</label></td>");
                    out.print("<td><label name=grade>"+schoolroll.getGrade()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>入学时间：</label></td>");
                    out.print("<td><label name=adDate>"+schoolroll.getAdDate()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>学制：</label></td>");
                    out.print("<td><label name=educational>"+schoolroll.getEducational()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>预计毕业时间：</label></td>");
                    out.print("<td><label name=gradDate>"+schoolroll.getGradDate()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>身份证号：</label></td>");
                    out.print("<td><label name=userId>"+schoolroll.getUserId()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>修读方式：</label></td>");
                    out.print("<td><label name=studyMode>"+schoolroll.getStudyMode()+"</label></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td align=right><label>学历：</label></td>");
                    out.print("<td><label name=qualification>"+schoolroll.getQualification()+"</label></td>");
                    out.print("</tr>");
                }
            %>
        </table>
    </form>


    <a href="schoolMenu.jsp">返回学业信息菜单</a>
    <button onclick="window.print()" type="button">打印</button>
</center>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
