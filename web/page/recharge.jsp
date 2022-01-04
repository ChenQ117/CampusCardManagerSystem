<%@ page import="vo.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>充值</title>
</head>
<body>
    <center>
        <form action="Recharge">
            <table>
                <tr>
                    <td align="right"><label>卡内余额：</label></td>
                    <td><%=((Card)session.getAttribute("user")).getMoney()%></td>
                </tr>
                <tr>
                    <td align="right"><label>充值金额：</label></td>
                    <td><input name="money" type="number" value="0"></td>
                </tr>
                <tr>
                    <td align="right"><label>支付方式：</label></td>
                    <td>
                        <select name="payment">
                            <option value="微信">微信</option>
                            <option value="支付宝">支付宝</option>
                            <option value="银行卡">银行卡</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="充值">
                        <a href="rechargeMenu.jsp">返回充值菜单</a>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
