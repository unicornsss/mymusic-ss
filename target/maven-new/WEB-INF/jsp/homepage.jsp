<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/10
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script src="/js/jquery.min.js"></script>
    <%--<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>--%>
    <script type="text/javascript">
        function test(keyword,event){
            //定义全局变量
            var sel = document.getElementById("sel");
            $.ajax({
                url : "/music/doResearch",
                type : "GET",
                dataType:"json",
                contentType : "application/json;charset=UTF-8",
                //<!-- 向后端传输的数据 -->
                data :{
                    key: $("#search_input").val()
                },
                success:function(result) {
//                    alert(result);
                    //<!-- 处理后端返回的数据 -->
                    var j = 0;
                    for(var i=0;i<result.length;i++){
                        //将当前循环满足条件的商品名称生成一个下拉的选项
                        sel.options[j]=new Option(result[i].songname,j);
                        sel.options[j+1]=new Option(result[i].singername,j+1);
                        j = j+2;
                    }
                    //自动设置高度
                    sel.size=(result.length)*2;
                    //判断是否有满足条件的商品
                    if(result.length>0){
                        sel.style.display='block';
                    }else{
                        sel.style.display='none';
                    }
                },
                error:function(result){
//                    alert("error");
                }
            });
            //当用户按下上下键时获取相应的值
            if(event.keyCode==40){
                sel.focus();
            }

        };

        function test2(){
            //将选中的下拉列表中的内容添加到输入框中
            $("#search_input").val($("option:selected").text());
            //输入回车，获取输入框内容焦点
            $("#sel").keypress(function(){
                $("#search_input").focus();
                $("#search_input").attr("onkeyup","");
                $("#sel").hide();

            });
            //双击，获取输入框内容焦点
            $("#sel").click(function(){
                $("#search_input").focus();
                $("#sel").css("display","none");
                /* var keyword=$("#search_input").val();
                 location.href="/blog/user/search?searchid="+keyword;*/
            });
        }
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="/other/logo.png" alt=""/></li>
        <li class="topa" id="top-color"><a href="/music/homePage"><span>音乐馆</span></a></li>
        <li class="topa"><a href="/music/userPage"><span>我的音乐</span></a></li>
        <li id="top-input">
            <form action="<%=request.getContextPath()%>/music/researchSong" method="post" charset=utf-8" >
            <input type="text" id="search_input" onkeyup="test(this.value,event)" name="keys"/>
            <%--<button>查询</button>--%>
                <input type="submit" value="查询"/>
            </form>
            <select multiple="multiple" id="sel" onchange="test2()" style="position:absolute;top:68px; left:780px; z-index:2;width:300px;display:none;color:gray"></select>
        </li>
        <!--可以退出-->
        <li id="top-5"><a href="">${userShow.username}</a></li>
        <li id="top-6"><a href="/music/outLogin">退出</a></li>
    </ul>
</div>
<div id="page-menu">
    <ul>
        <li><span id="select-btn2"><a href="/music/homePage">首页</a></span></li>
        <li><span><a href="/music/showSingers">歌手</a></span></li>
        <li><span><a href="/music/showSpe">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="new-spe">
        <span class="page-spe-name">歌曲推荐</span>
        <ul class="page-every-spe">
            <c:forEach items="${randSong}" var="randSong">
            <li>
                <div><a href=""><img src="/img/${randSong.songPicture}" alt=""/></a>
                <span><a href="/music/showEverySong?songId=${randSong.id}">${randSong.songName}</a></span>
                    <%--<span>播放量${randSong.songPlayNum}</span>--%>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div>
    <br/>
    <div class="new-song">
        <span class="page-spe-name">专辑推荐</span>
        <ul class="page-every-spe">
            <c:forEach items="${randSpe}" var="randSpe">
            <li><div><a href=""><img src="/files/${randSpe.specialPicture}" alt=""/></a>
                <span><a href="/music/showEverySpe?speId=${randSpe.id}">${randSpe.specialName}</a></span><span>${randSpe.singer.singerName}</span></div>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
