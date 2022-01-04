<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>按姓名查询</title>
</head>

<body>
    <center>
        <form action="QueryByName">
            <input type="text" name="query">
            <input type="submit" value="查询"></input>
            <a href="../index_2.jsp">返回主页</a>
            <a href="Logout">注销</a>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>