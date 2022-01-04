<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>一卡通管理系统</h1>
    <hr color="#660">

<%
    String error =(String) request.getAttribute("error");
    if (error!=null){
        out.print("<center><font color=red>error:"+error+"</font></center>");
        request.setAttribute("error",null);
    }
%>
</body>
</html>
