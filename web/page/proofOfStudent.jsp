<%@ page import="vo.SchoolRoll" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在校生证明</title>
</head>
<body>
    <center>

        <div style="width: 50%;position: center">
            <h1>黑龙江大学</h1>
            <h4>地址：哈尔滨市南岗区学府路74号（150080）&nbsp 电话：0451-86608476</h4>
            <hr color="black">
            <hr color="gray">
            <h2>在校学生证明</h2>

            <font>
                <%
                    Object school = session.getAttribute("schoolroll");
                    if (school!=null){
                        SchoolRoll schoolRoll = (SchoolRoll) school;
                        out.print("<p>");
                        out.print("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");

                        out.print(schoolRoll.getName()+",学号："+schoolRoll.getUserCardId()
                                +","+schoolRoll.getGender()+",身份证号："+schoolRoll.getUserId()+"，于"
                                +schoolRoll.getAdDate()+"入学，系我校"+schoolRoll.getCollage()+schoolRoll.getMajor()
                                +schoolRoll.getQualification()+"学生，学制"+schoolRoll.getEducational()
                                +",在校"+schoolRoll.getGrade()+"级学习，属"+schoolRoll.getStudyMode()+"学生。"
                        );
                        out.print("</p>");
                        out.print("<p>");
                        out.print("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");

                        out.print("依据《黑龙江大学学生管理规定》，如修完教学计划规定的全部课程，" +
                                "成绩合格，达到毕业标准，预计于"+schoolRoll.getGradDate()+"毕业。");
                        out.print("</p>");
                        out.print("特此证明。");
                        out.print("<br>");
                    }
                %>
                <div style="position: absolute;bottom: 20%;right: 27%">
                    <font >黑龙江大学学生处学籍科</font>
                    <br>
                    <font><%=new SimpleDateFormat("YYYY年MM月dd日").format(new Date())%></font>
                </div>
            </font>
            <div style="position: absolute;bottom: 5%;right: 60%">
                <a href="schoolMenu.jsp">返回学业信息菜单</a>

                <button onclick="window.print()">打印</button>
            </div>
        </div>
    </center>
</body>
</html>
