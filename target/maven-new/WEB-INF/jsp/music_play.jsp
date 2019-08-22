<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="imageBg">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="a collection of songs that I love, also shows how to sync lyric with HTML5 audio tag">
    <meta name="author" content="Wayou">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>精選 | a collection of songs that I love, also shows how to sync lyric with HTML5 audio tag</title>
    <link rel="stylesheet" href="/css/style.css">
    <%--<link rel="stylesheet" href="styles/style.css">--%>
    <script>
        <%--var value = '<%=request.getSession().getAttribute("result")%>';--%>
        <%--alert(value);--%>
        <%--alert("222");--%>
        function CloseOpen(){
            if(event.clientX <= 0 && event.clientY<0) {
//                alert("关闭");
//                window.open('clearCookie.aspx')
                window.location = "logout.jsp";
            }
            else
            {
//                alert("刷新或离开");
            }
        }
    </script>
    <script language="text/javascript">
        var clicked = false;
            function CheckBrowser() {
            if (clicked == false){
    //页面关闭
    }
    else {
    //重定向
    clicked = false;
    }
    }

    function bodyUnload() {
    if (clicked == false)//页面关闭{
    var request = GetRequest();
    request.open("GET", "/jsp/LogOut.aspx", true);
    request.send();
    }
    function GetRequest() {
    var xmlHttp = null;
    try {
    // Firefox, Opera 8.0+, Safari
    xmlHttp = new XMLHttpRequest();
    }
    catch (e) {
    //Internet Explorer
    try {
    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e) {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    }
    return xmlHttp;
    }
    //]]>
    </script>
</head>
<%--<body onbeforeunload = "window.location='logout.jsp'">--%>
<%--<body onbeforeunload = CloseOpen()>--%>
<body onunload=bodyUnload() onclick="clicked=true;">
<header>
    <h1> <a href="https://github.com/wayou/selected/" target="_blank" title="view on GitHub">精選</a></h1><sub>a collection of songs that I love</sub>
    <a href="/music/outPlay">退出播放</a>
</header>
<input id="PageContext" type="hidden" value='<%=request.getSession().getAttribute("result")%>' />
<div class="wrapper">
    <div id="player">
        <audio controls id="audio" src="./content/songs/na_ge.mp3">!audio not supported :(</audio>
    </div>
    <div id="playlist">
        <ol>
        </ol>
        <div class="info">
            <div class="bg" id="bg_dark" title="use color for background"></div>
            <div class="bg" id="bg_pic" title="use image for background"></div>
            <small> view on <a target="_blank" href="https://github.com/wayou/selected/">GitHub</a></small>
        </div>
    </div>
    <div id="lyricWrapper">
        <div id="lyricContainer">
        </div>
    </div>
</div>
<%--<script src="scripts/selected.js"></script>--%>
<script src="/other/selected.js"></script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-46794744-6', 'wayou.github.io');
    ga('send', 'pageview');
</script>
</body>
</html>