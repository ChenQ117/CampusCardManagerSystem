<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>学业信息查询菜单</title>
</head>
<body>
    <a href="#"  onclick="ajaxRequest('schoolRoll.jsp','schoolroll')">学籍</a><br>
    <a href="#" onclick="ajaxRequest('proofOfStudent.jsp','schoolroll')">在校生证明</a><br>
    <a href="#" onclick="ajaxRequest('schoolTranscript.jsp','transcript')">成绩单</a><br>
</body>
</html>
<script>
    function ajaxRequest(url,query) {
        var xhttp;
        //1.创建对象
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhttp.open("GET","SchoolAffairCenter?query="+query,true);
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState==4&&xhttp.status==200)
                window.location.assign(url);
        }
    }
</script>
<%@ include file="../include/footer.jsp" %>
