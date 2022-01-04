<%@ page import="vo.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>选座菜单</title>
</head>
<body>
    <a href="ChooseChair">选座</a><br>
    <a href="CancelChair">取消选座</a><br>
    <a href="../index_<%=((Card)session.getAttribute("user")).getType()%>.jsp">返回首页</a><br>

</body>
</html>
<%@ include file="../include/footer.jsp" %>
