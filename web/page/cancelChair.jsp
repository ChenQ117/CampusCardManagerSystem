<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>取消选座</title>
</head>
<body>
    <%
        Object info = session.getAttribute("info");
        if (info!=null){
            out.print("<h3>"+info+"...</h3>");
        }
    %>
    <a href="chairMenu.jsp">返回选座菜单</a>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
