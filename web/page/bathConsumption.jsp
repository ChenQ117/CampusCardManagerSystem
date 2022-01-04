<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<head>
    <title>洗浴消费</title>
</head>
<body>
    <center>
        <form name="myForm" action="Bath">
            <input id="consumption" name="bathMoney" value="0.00">
            <a href="#" id="start" onclick="<%=session.getAttribute("isBathing")!=null&&(boolean)session.getAttribute("isBathing")?"timedMsg()":"start()"%>">开始</a>
            <button type="button" id="btStop" value="暂停" style="display: none" onclick="stop()">暂停</button>
            <button type="button" id="end"  onclick="ajaxRequest()">结束</button>
            <input id="status" name="status" style="display: none" value="">
        </form>
    </center>
</body>
<script>
    var money=3.00;
    var t;
    var isBathing=false;
    function start() {
        alert("start");
        // isBathing = true;
        document.getElementById("status").value="start";
        document.getElementById('consumption').value= money;
        document.myForm.submit();
    }
    function timedMsg() {
        isBathing=true;
        document.getElementById('consumption').value= money.toFixed(2);
        document.getElementById('consumption').disabled=true;
        if (document.getElementById("btStop").style.display =="none"){
            document.getElementById("btStop").style.display ="";
        }
        if(money<=0){
            var flag = confirm("是否继续？");
            clearTimeout(t);
            if (flag){
                // document.getElementById("consumption").value=money;
                document.getElementById("status").value="start";
                document.myForm.submit();
                t=setTimeout('timedMsg()',1000);
            }else {
                isBathing=false;
                // document.getElementById("consumption").value=money;
                document.getElementById("status").value="end";
                document.myForm.submit();
            }
        }else{
            t=setTimeout('timedMsg()',1000);
        }
        money-=0.01;
        // money+=0.01
    }
    function stop() {
        if (isBathing){
            clearTimeout(t);
            isBathing=false;
            document.getElementById("btStop").value = "继续";
        }else {
            t=setTimeout('timedMsg()',1000);
            isBathing=true;
            document.getElementById("btStop").value = "暂停";
        }
    }
    function end() {
        clearTimeout(t);
        document.getElementById("status").value="end";
        isBathing=false;
        ajaxRequest();
    }
    function ajaxRequest() {
        clearTimeout(t);
        document.getElementById("status").value="end";
        isBathing=false;

        var xhttp;
        //1.创建对象
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhttp.open("GET","Bath?bathMoney="+document.getElementById('consumption').value+"&status="+document.getElementById("status").value,true);
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState==4&&xhttp.status==200)
                window.location.assign("consumptionMenu.jsp");
        }
    }
</script>
</html>
<%@ include file="../include/footer.jsp" %>
