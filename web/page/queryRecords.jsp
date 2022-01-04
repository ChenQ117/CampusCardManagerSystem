<%@ page import="java.util.List" %>
<%@ page import="vo.FinancialRecord" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>查询消费记录</title>
</head>
<body>

    <center>
        <form action="#" name="myForm">
            <table width="100%">
                <%--        表头行--%>
                <tr>
                    <th>序号</th>
                    <th>卡号</th>
                    <th>交易时间</th>
                    <th>交易金额</th>
                    <th>支付方式</th>
                </tr>
                <%
                    if (session.getAttribute("pageCount")==null){
                        session.setAttribute("pageCount",10);
                    }
                    int pageCount = (int)session.getAttribute("pageCount");
                    int perPage;
                    if (request.getParameter("page")==null){
                        if (session.getAttribute("perPage")==null){
                            perPage = 1;
                        }else {
                            perPage = (int)session.getAttribute("perPage");
                        }
                    }else {
                        perPage = Integer.valueOf(request.getParameter("page"));
                    }
                    List<FinancialRecord> records =(List<FinancialRecord>) session.getAttribute("records");
                    int lastPage = (records==null||records.size()==0)?1: (int)Math.ceil(records.size()/Double.valueOf(pageCount));
                    perPage = perPage>lastPage?lastPage:perPage;
                    session.setAttribute("perPage",perPage);
                    int k = 0;
                    for(int i=0;i<pageCount;i++){
                        k=i+(perPage-1)*pageCount;
                        if (k<records.size()){
                            FinancialRecord record = records.get(k);
                            out.print("<tr align=center>");
                            out.print("<td>"+(k+1)+"</td>");
                            out.print("<td><label name=cardId>"+record.getCardId()+"</label></td>");
                            out.print("<td><label name=payTime>"+record.getPayTime()+"</label></td>");
                            out.print("<td><label name=payMoney>"+record.getPayMoney()+"</label></td>");
                            out.print("<td><label name=payment>"+record.getPayment()+"</label></td>");
                            out.print("</tr>");
                        }else {
                            break;
                        }
                    }
                    session.setAttribute("perCount",k+1);
                %>
            </table>
        </form>

        <a href="queryRecords.jsp?page=1">|<<1</a>
        <a href="queryRecords.jsp?page=<%=(perPage-1)<1?1:(perPage-1)%>"><<</a>
        <%
            for (int i=0;i<4;i++){
                int index = perPage+i;
                if (index<=lastPage&&index>0){
                    out.println("<a href=queryRecords.jsp?page="+index+">"+index+"</a>");
                }else {
                    break;
                }
            }
            if (perPage!=lastPage && lastPage>4){
                out.println("...");
            }
        %>
        <a href="queryRecords.jsp?page=<%=(perPage+1)>lastPage?lastPage:(perPage+1)%>">>></a>
        <a href="queryRecords.jsp?page=<%=lastPage%>"><%=lastPage==0?1:lastPage%>>>|</a>
        <br>
        <a href="rechargeMenu.jsp">返回充值菜单</a>
    </center>
</body>
</html>
<%@ include file="../include/footer.jsp" %>
