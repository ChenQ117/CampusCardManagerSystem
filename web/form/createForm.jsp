<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<html>
<head>
    <title>创建校园卡用户</title>
</head>
<body>
    <h4>创建用户：</h4>
    <center>
        <form name="form" action="createUser">
            <table>
                <tr>
                    <td align="right">用户类型：</td>
                    <td><input name="type" id="S"  type="radio" value="S" checked onclick="show()" >学生
                        <input name="type" id="T" type="radio" value="T"  onclick="show()">教师</td>
                </tr>
                <tr>
                    <td align="right">用户密码：</td>
                    <td><input name="password"type="password" value="000000"></td>
                </tr>
                <tr>
                    <td align="right">账户余额：</td>
                    <td><input name="money" type="number" value="0"></td>
                </tr>
                <tr>
                    <td align="right">选座权限：</td>
                    <td><input name="root"type="number" value="1"></td>
                </tr>
                <tr id="name">
                    <td align="right">姓名：</td>
                    <td><input name="name" value="张三"></td>
                </tr>
                <tr id="gender" >
                    <td align="right">性别：</td>
                    <td><input name="gender" type="radio" value="男" checked>男
                        <input name="gender" type="radio" value="女">女</td>
                </tr>
                <tr id="collage">
                    <td align="right">学院：</td>
                    <td><input name="collage" value="计算机科学技术学院"></td>
                </tr>
                <tr id="major">
                    <td align="right">系：</td>
                    <td><input name="major" value="计算机科学与技术专业"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="添加">
                        <input type="reset" value="重置">
                        <a href="../index_0.jsp">返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<script>
    function show() {
        if (document.form.type.value == "S"){
            document.getElementById("name").style.display=''
            document.getElementById("collage").style.display=''
            document.getElementById("gender").style.display=''
            document.getElementById("major").style.display=''

        }else {
            document.getElementById("name").style.display='none'
            document.getElementById("collage").style.display='none'
            document.getElementById("gender").style.display='none'
            document.getElementById("major").style.display='none'
        }
    }
</script>
<%@ include file="../include/footer.jsp"%>