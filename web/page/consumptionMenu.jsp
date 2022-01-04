<%@ page import="vo.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>消费菜单</title>
</head>
<body>
<a href="shoppingConsumption.jsp">校内购物</a><br>
<a href="bathConsumption.jsp">校内洗浴</a><br>
<a href="../index_<%=((Card)session.getAttribute("user")).getType()%>.jsp">返回首页</a><br>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
