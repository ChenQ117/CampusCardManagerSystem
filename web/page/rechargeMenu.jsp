<%@ page import="vo.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>充值菜单</title>
</head>
<body>
    <a href="recharge.jsp">充值</a><br>
    <a href="QueryRecords">查询消费记录</a><br>
    <a href="../index_<%=((Card)session.getAttribute("user")).getType()%>.jsp">返回首页</a><br>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
