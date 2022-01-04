<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>按卡号查询</title>
</head>

<body>
<center>
    <form action="DeleteUser">
        <input type="number" name="userCardId">
        <input type="submit" value="删除" onclick="confirm('确认删除？')">
        <a href="../index_0.jsp">返回主页</a>
        <a href="Logout">注销</a>
    </form>
</center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>