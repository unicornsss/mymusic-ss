<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/13
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>歌手</title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function(){
            var btnFlag = 0;
            $(".every-song").mouseenter(function(){
                if(btnFlag ==0){
                    showbtn();
                }
            });
            $(".every-song").mouseleave(function(){
                $(".song-name").next().remove();
                btnFlag = 0;
            });
            $(".topa a").click(function(){
                $(this).css("background",'#35ffaa');
            });
            function showbtn(){
                $(".every-header").append('<div class="btn-play"><a href=""><span>播放</span></a><a href=""><span>+</span></a></div>');
                btnFlag = 1;
            }

//            var btnSingeFlag = 0;
//            $(".notice-singer-btn").click(function () {
//                if(btnSingeFlag == 0){
//                    $(this).html("关注");
//                    btnFlag ++;
//                }else{
//                    $(this).html("关注");
//                    btnFlag ++;
//                }
//            });
        });
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="/other/logo.png" alt=""/></li>
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
        <li><span><a href="/music/homePage">首页</a></span></li>
        <li><span id="select-btn2"><a href="/music/showSingers">歌手</a></span></li>
        <li><span><a href="">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="new-spe">
        <!--<span class="page-spe-name">歌单推荐</span>-->
        <ul class="page-show-singer">
            <c:forEach items="${pageSinger.lists}" var="singer">
                <a href="/music/showEverySinger?singerId=${singer.id}">
                    <div class="follow-singer"><img src="/files/${singer.singerPicture}" alt=""/>
                        <span>${singer.singerName}</span><br/>
                            <%--<span>111粉丝</span><br/>--%>
                        <%--<span class="btn-notice-singer">已关注</span>--%>
                    </div>
                </a>
            </c:forEach>
        </ul>
    </div>
    <div id="page-menu-btn">
        <table  border="0" cellspacing="0" cellpadding="0"  width="400px">
            <tr>
                <td class="td2">
                    <span>第${requestScope.pageSinger.currPage }/ ${requestScope.pageSinger.totalPage}页</span>
                    <span>总记录数：${requestScope.pageSinger.totalCount }  每页显示:${requestScope.pageSinger.pageSize}</span>
                   <span>
                       <c:if test="${requestScope.pageSinger.currPage != 1}">
                           <a href="${pageContext.request.contextPath }/music/showSingers?currentPage=1">[首页]</a>
                           <a href="${pageContext.request.contextPath }/music/showSingers?currentPage=${requestScope.pageSinger.currPage-1}">[上一页]</a>
                       </c:if>
                       <c:if test="${requestScope.pageSinger.currPage != requestScope.pageSinger.totalPage}">
                           <a href="${pageContext.request.contextPath }/music/showSingers?currentPage=${requestScope.pageSinger.currPage+1}">[下一页]</a>
                           <a href="${pageContext.request.contextPath }/music/showSingers?currentPage=${requestScope.pageSinger.totalPage}">[尾页]</a>
                       </c:if>
                   </span>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
