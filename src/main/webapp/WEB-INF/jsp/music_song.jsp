<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/20
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function(){
            $("#commit-btn").click(function(){
                if($("#comment-text").val()!= null){
                    $.ajax({
                        url:"/music/addComment",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{ songId: ${songShow.id},
                        text: $("#comment-text").val() },
                        success:function(data)
                        {
                            if(data.result =="insertSuccess"){
                                window.open("/music/showEverySong?songId=${songShow.id}","_self");
                                console.log("关注成功"+data.result);
                            }else{
                                console.log("关注失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("关注失败");
                        }
                    });
                    $("#comment-text").val()
                }
            });

            var noticeFlag = 0;
            $("#singer-notice-btt").click(function(){
                if($(this).html() =="我喜欢" ){
//                    alert($(this).html());
                    $(this).html("已喜欢");
                    noticeFlag ++;
                    $.ajax({
                        url:"/music/addLikeSong",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{songId: ${songShow.id}},
                        success:function(data)
                        {
//                            alert(data);
                            if(data.result =="addSuccess"){
                                console.log("关注成功"+data.result);
                            }else{
                                console.log("关注失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("关注失败");
//                            alert("error");
                        }
                    });
//                    alert($(this).attr('id'));
                }else{
                    $(this).html("我喜欢");
                    noticeFlag --;
                    $.ajax({
                        url:"/music/deleteLikeSong",
                        type: 'post',
                        dataType: 'json',
                        data:{songId: ${songShow.id}
                        },
                        success:function(data) {
                            if(data.result =="updateSuccess"){
                                console.log("删除成功"+data.result);
                            }else{
                                console.log("删除失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("删除失败");
                        }
                    })
                }
            });

            $("#singer-play-btt").click(function(){
                var a ='<%=session.getAttribute("result")%>';
                if(a=="null"||a==""){
//                    alert("blank");
                window.open("/music/play?songId=${songShow.id}","_blank");
                }else{
//                    alert("not_blank");
                $.ajax({
                    url:"/music/play",
                    type: 'post',
                    dataType: 'json',
//                        data: {name:"你好"},
                    data:{songId: ${songShow.id}    },
                    success:function(data)
                    {
                        if(data.result =="addSuccess"){
                            console.log("关注成功"+data.result);
                        }else{
                            console.log("关注失败");
                        }
                        //Result为后端post函数传递来的数据，这里写结果操作代码
                    },error : function(xhr, status, errMsg)
                    {
                        console.log("关注失败");
                    }
                });
                }
            });

            $(".comment-zan-btn").click(function(){
                $.ajax({
                    url:"/music/addCommentZan",
                    type: 'post',
                    dataType: 'json',
                    data:{commentId: $(this).attr('property') },
                    success:function(data)
                    {
                        if(data.result =="zanSuccess"){
                            window.open("/music/showEverySong?songId=${songShow.id}","_self");
                            console.log("关注成功"+data.result);
                        }else{
                            console.log("关注失败");
                        }
                        //Result为后端post函数传递来的数据，这里写结果操作代码
                    },error : function(xhr, status, errMsg)
                    {
                        console.log("关注失败");
                    }
                });
            })
        });
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="../other/logo.png" alt=""/></li>
        <li class="topa" id="top-color"><a href="/music/homePage"><span>音乐馆</span></a></li>
        <li class="topa"><a href="/music/userPage"><span>我的音乐</span></a></li>
        <li id="top-input"><div><input type="text"/></div></li>
        <!--可以退出-->
        <li id="top-5"><a href="">${userShow.username}</a></li>
        <li id="top-6"><a href="/music/outLogin">退出</a></li>
    </ul>
</div>

<div id="page-menu">
    <ul>
        <li><span><a href="">首页</a></span></li>
        <li><span><a href="">歌手</a></span></li>
        <li><span><a href="">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>

<div id="page-main">
    <div class="show-sin">
        <div id="every-singer-show">
            <img src="/img/${songShow.songPicture}" alt=""/>
            <div id="song-details">
            <span id="singer-de-name">${songShow.songName}</span><br/>
            <span class="song-de">专辑：${songShow.special.specialName}</span>
            <span class="song-de">语种：${songShow.songLanguage}</span>
            <span class="song-de">流派：${songShow.songStyle}</span>
            <%--<span class="song-de">唱片公司：${songShow.}</span>--%>
            <span class="song-de">发行时间：${songShow.createTime}</span>
            <%--<p id="singer-de-mes">专辑简介：${son.specialSummary}</p><br/>--%>
            <%--<!--<button id="singer-notice-btt" property="${special.id}">已收藏</button>-->--%>
            <button id="singer-play-btt" property="${songShow.id}">播放</button>
                <c:if test="${LikeFlag == true}">
                    <button id="singer-notice-btt" property="${songShow.id}">已喜欢</button>
                    <%--<button id="singer-notice-btt" property="${singer.id}">已关注</button>--%>
                </c:if><c:if test="${LikeFlag == false}">
                <button id="singer-notice-btt" property="${songShow.id}">我喜欢</button>
                <%--<button id="singer-notice-btt" property="${singer.id}">关注</button>--%>
            </c:if>
        </div>
        </div>
        </div>
            <br/><br/><br/>
            <div id="show-song">
            <div id="show-lrc">
            <span>歌词</span>
            <%--<pre>--%>
                <div>
            <c:if test="${not empty str}">
                <c:forEach items="${str}" var="str">
                    ${str}
                    <br/>
                </c:forEach>
            </c:if>
            </div>
            <%--</pre>--%>
            </div>
            <div id="comment">
            <span id="comment-title">评论</span>
            <span>共${commentsNumber}条评论</span>
            <br/>
            <table id="commit-comment">
            <textarea name="" id="comment-text"></textarea>
            <br/>
            <button id="commit-btn">提交</button>
            </table>
                <c:if test="${not empty comments}">
                <c:forEach items="${comments}" var="comment" varStatus="status">
                    <div class="every-comment" style="float: left;">
                        <%--<div><img src="../files/1540976695822&&tablo.jpg" alt="" /></div>--%>
                        <div><img src="/files/${comment.user.userPic}" alt="" /></div>
                            <a href="/music/showUser?userId=${comment.userId}"><span class="comment-name">${comment.user.username}</span></a> <span>${comment.createTime}</span>
                            <button class="comment-zan-btn" property=${comment.id}>赞</button>
                            <span>${numResult[status.index]}</span>
                        <!--<p>高考前每天听这首歌曲 给了我很多力量 现在已经工作两年 身处西非尼日利亚 今天听到了一个黑人在哼唱这首歌 瞬间回到了那一年 那一个盛夏 </p>-->
                        <p>
                            ${comment.content}
                        </p>
                    </div>
                </c:forEach>
                </c:if>
            </div>
            </div>
        </div>
</body>
</html>
