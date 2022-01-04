<%@ page import="java.util.List" %>
<%@ page import="vo.Chair" %>
<%@ page import="utils.R" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>选座</title>
</head>
<body bgcolor="#faf6ed" background="../image/library.jpg" style="background-size: 100% ;" >
    <%
        String seatInfo =(String) request.getAttribute("seatInfo");
        if (seatInfo!=null){
            out.print("<center><font size=6 color=#f2b184>"+seatInfo+"</font></center>");
            request.setAttribute("seatInfo",null);
        }
        Object o = session.getAttribute("chairList");
        if (o!=null){
            List<Chair> chairList = (List<Chair>) o;
    %>
    <center>
        <form action="#" name="myForm">
            <table width="80%" >
                <%--        表头行--%>
                <tr>
                    <th>选择座位</th>
                    <th>座位编号</th>
                    <th>使用状态</th>
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
                    int lastPage = (chairList==null||chairList.size()==0)?1: (int)Math.ceil(chairList.size()/Double.valueOf(pageCount));
                    perPage = perPage>lastPage?lastPage:perPage;
                    session.setAttribute("perPage",perPage);
                    int k = 0;
                    for(int i=0;i<pageCount;i++){
                        k=i+(perPage-1)*pageCount;
                        if (k<chairList.size()){
                            Chair chair = chairList.get(k);
                            out.print("<tr align=center>");
                            out.print("<td><input type=radio name=chooseChairId value="+chair.getChairId()+"></td>");
                            out.print("<td><label name=chairId>"+chair.getChairId()+"</label></td>");
                            if (chair.getStatus()== R.CHAIR_AVAILABLE){
                                out.print("<td><label style='color: #a95dba' name =status>可用</label></td>");
                            }else {
                                out.print("<td><label style='color: #f25f3e' name =status>不可用</label></td>");
                            }
//                            out.print("<td><label name=status>"+(chair.getStatus()==1?"可用":"不可用")+"</label></td>");
                            out.print("</tr>");
                        }else {
                            break;
                        }
                    }
                    session.setAttribute("perCount",k+1);
                %>
                <tr>
                    <td colspan="2" align="right"><label>时长：</label></td>
                    <td><select name="hour">
                        <option value="1">1h</option>
                        <option value="2">2h</option>
                        <option value="3">3h</option>
                        <option value="4">4h</option>
                    </select></td>
                </tr>
            </table>
        </form>

        <a href="chooseChair.jsp?page=1">|<<1</a>
        <a href="chooseChair.jsp?page=<%=(perPage-1)<1?1:(perPage-1)%>"><<</a>
        <%
            for (int i=0;i<4;i++){
                int index = perPage+i;
                if (index<=lastPage&&index>0){
                    out.println("<a href=chooseChair.jsp?page="+index+">"+index+"</a>");
                }else {
                    break;
                }
            }
            if (perPage!=lastPage && lastPage>4){
                out.println("...");
            }
        %>
        <a href="chooseChair.jsp?page=<%=(perPage+1)>lastPage?lastPage:(perPage+1)%>">>></a>
        <a href="chooseChair.jsp?page=<%=lastPage%>"><%=lastPage==0?1:lastPage%>>>|</a>
        <br>
        <a href="#" onclick="choose()">确认选座</a>
    </center>
    <%
        }
    %>
    <center>
        <a href="chairMenu.jsp">返回选座菜单</a>
    </center>
</body>
<script>
    function choose() {
        document.myForm.action="ChooseChair";
        document.myForm.submit();
    }
</script>
</html>
<%@ include file="../include/footer.jsp" %>

