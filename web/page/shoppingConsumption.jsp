<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>校内购物</title>
</head>
<body>
    <center>
        <form action="Shopping">
            <table>
                <tr>
                    <td align="right"><label>选择物品：</label></td>
                    <td>
                        <select name="woods">
                            <option value="10">物品1 10.0</option>
                            <option value="18">物品2 18.0</option>
                            <option value="7">物品3 7.0</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="购买">
                        <a href="consumptionMenu.jsp">返回消费菜单</a>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
