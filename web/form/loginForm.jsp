<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>登录表单</title>
</head>
<%
    String registerInfo = (String)request.getAttribute("registerInfo");
    if (registerInfo!=null){
        out.print("<center><font>"+registerInfo+"</font></center>");
        request.setAttribute("registerInfo",null);
    }
    String username ="";
    String password ="";
    Cookie[] cookies = request.getCookies();
    if (cookies!=null)
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("username")){
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }
        }
%>
<body>
    <font>用户登录：</font>
    <center>
        <form action="Login">
            <table>
                <tr>
                    <td align="right"><label>用户登录：</label></td>
                    <td><input type="text" name="username" value="<%=username%>"></td>
                </tr>
                <tr>
                    <td align="right"><label>用户密码：</label></td>
                    <td><input type="password" name="password" value="<%=password%>"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="登录">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp"%>