<%@ page import="java.util.List" %>
<%@ page import="vo.PhysicalItem" %>
<%@ page import="vo.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>

<html>
<head>
    <title>录入体测成绩</title>
</head>
<body>
    <center>
        <table>
            <tr>
                <td align="right"><label>输入学生学号：</label></td>
                <td><input id="cardId" name="cardId"></td>
                <td colspan="2" align="center"><button id="confirm" onclick="ajaxRequest('physicalTest.jsp','confirm',0)">核对</button></td>
            </tr>
        </table>
        <hr color="#660">
        <h4 align="left">请录入成绩：</h4>
        <form action="PhysicalTest">
            <table>

                <%
                    Student student = (Student) session.getAttribute("student");
                    if (student!=null){
                %>
                <tr>
                    <td align="right"><label>学生学号：</label></td>
                    <td><input name="cardId" id="cardId2" value="<%=student.getUserCardId()%>"></td>
                </tr>
                <tr>
                    <td align="right"><label>学生姓名：</label></td>
                    <td><input id="name" name="name" value=<%=student.getName()%>></td>
                </tr>
                <tr>
                    <td align="right"><label>学生性别：</label></td>
                    <td><input value=<%=student.getGender()%>></td>
                </tr>
                <tr>
                    <td align="right"><label>体测项目：</label></td>
                    <td><select id="item" name="item">
                <%
                    Object o = session.getAttribute("physicalList");
                    if (o!=null){
                        List<PhysicalItem> physicalList = (List<PhysicalItem>) o;
                        if (physicalList!=null){
                            int i=0;
                            for (PhysicalItem p:physicalList){
                                if (i==0){
                                    out.print("<option value="+p.getItemId()+">"+p.getItemName()+"</option>");
                                    i++;
                                }else {
                                    out.print("<option value="+p.getItemId()+">"+p.getItemName()+"</option>");
                                }
                            }
                        }
                    }

                %>
                    </select></td>
                </tr>
                <tr>
                    <td align="right"><label>该项目成绩：</label></td>
                    <td><input name="score" id="score" value="0"></td>
                </tr>
                <tr>
                    <td align="right"><label>本次体测时间：</label></td>
                    <td><input name="stTime" id="stTime" ></td>
                </tr>
                <tr>
<%--                    onclick="ajaxRequest('../index_1.jsp','',1)"--%>
                    <td align="right"><button type="button" onclick="ajaxRequest('../index_1.jsp','',1)" >录入</button></td>
                    <td align="left"><button type="button" onclick="window.location.assign('../index_1.jsp')">取消</button></td>
                </tr>
                <%
                    }
                    session.setAttribute("student",null);
                %>
            </table>
        </form>
    </center>

</body>

<script>
    function ajaxRequest(url,query,index) {
        var xhttp;
        var u = ["PhysicalTest?query="+query+"&cardId="+document.getElementById("cardId").value,
            "PhysicalTest?query="+query+"&cardId="+document.getElementById("cardId2").value+
            "&item=" + document.getElementById("item").value+
            "&score="+document.getElementById("score").value+
            "&name="+document.getElementById("name").value+
            "&stTime="+document.getElementById("stTime").value
        ];
        //1.创建对象
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhttp.open("GET",
            u[index],
            true);
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState==4&&xhttp.status==200)
                window.location.assign(url);
        }
    }
</script>
</html>
<%@ include file="../include/footer.jsp" %>
