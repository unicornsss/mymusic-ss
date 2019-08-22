<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/16
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function(){
            var btnFlag = 0;
            $(".every-list").mouseenter(function(){
                if(btnFlag ==0){
//                    $(".btn-play").css('display','block');
                    $(this).children(".mylist-myname").children(".btn-play").css('display','block');
//                    $(this).children(".mylist-allplay").children(".btn-list-del").css('display','block');
                    $(this).children(".mylist-allplay").children(".do-list-del").css('display','block');
//          showbtn();
//          $(".btn-play").show("fast",callback);
                    btnFlag = 1;
                }
            });
            $(".every-list").mouseleave(function(){
                $(this).children(".mylist-myname").children(".btn-play").css('display','none');
//                $(this).children(".mylist-allplay").children(".btn-list-del").css('display','none');
                $(this).children(".mylist-allplay").children(".do-list-del").css('display','none');

                btnFlag = 0;
            });
            $(".do-list-del").click(function () {
                $(this).parent().parent().parent().remove();
                $.ajax({
                url:"/music/deleteList",
                type: 'post',
                dataType: 'json',
                data:{listId: $(this).attr('property') },
                success:function(data) {
                    if(data.result =="delSuccess"){
                        console.log("关注成功"+data.result);
                    }else{
                        console.log("关注失败");
                    }
                    //Result为后端post函数传递来的数据，这里写结果操作代码
                },error : function(xhr, status, errMsg)
                {
                    console.log("关注失败");
                }
            })
            });
        });
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="../other/logo.png" alt=""/></li>
        <li class="topa"><a href=""><span>音乐馆</span></a></li>
        <li class="topa"  id="top-color"><a href=""><span>我的音乐</span></a></li>
        <li id="top-input"><div>
            <%--<input type="text"/>--%>
        </div></li>
        <!--可以退出-->
        <li id="top-5"><a href="">${userShow.username}</a></li>
        <li id="top-6"><a href="/music/outLogin">退出</a></li>
    </ul>
</div>
<div class="show-img">
    <div >
        <img src="/files/${userShow.userPic}" alt="" id="show-img-self"/>
    </div>
    <h1>${userShow.username}</h1>
    <ul class="show-fan">
        <li>关注${userShow.noticeSinger}|</li>
        <li>|粉丝${userShow.fans}</li>
    </ul>
</div>
<div class="main">
    <div id="show-menu">
        <ul class="menu">
            <li><a href="">我喜欢</a></li>
            <li><a href="/music/showList" id="my-menu-label">我创建的歌单</a></li>
            <li><a href="/music/showNotice">关注</a></li>
            <li><a href="">粉丝</a></li>
        </ul>
        <%--<ul class="like">--%>
            <%--<li><a href="">歌曲</a></li>--%>
            <%--<li><a href="">歌单</a></li>--%>
            <%--&lt;%&ndash;<li><a href="">专辑</a></li>&ndash;%&gt;--%>
        <%--</ul>--%>
    </div><br/>
    <div id="addlist-menu">
        <form action="<%=request.getContextPath()%>/music/doAddlist" method="post" enctype="multipart/form-data" charset=utf-8"><table>
            <label id="add-mylist">请输入歌单名<input type="text" name="listName"/></label>
            <input type="submit" value="创建歌单" id="add-list"/>
        </table></form>
        <!--<a href=""><button id="add-list"> 创建歌单</button></a>-->
        <div id="show-mylist">
            <ul>
                <li class="mylist-name">歌单</li>
                <li class="mylist-number">曲目数</li>
                <li class="mylist-play-num">收听</li>
            </ul>
            <ul>
            <c:if test="${not empty songLists}">
                <c:forEach items="${songLists}" var="songLists" varStatus="status">
                    <li>
                        <div class="every-list">
                        <span class="mylist-myname"><a href="/music/updateMyList?songListId=${songLists.id}">${songLists.listName}</a>
                        <div class="btn-play"><a href=""><button>播放</button></a>
                        <select class="btn-list" ><option value="1" >添加到</option><option value="222">222</option></select></div>
                        </span>
                            <span class="mylist-allnum">${numResult[status.index]}</span>
                            <span class="mylist-allplay">${songLists.playNum}
                                <button class="do-list-del" property="${songLists.id}" style="float: right;display: none;width: 50px;height: 40px;">
                                    删除</button></span>
                        </div>
                    </li>
                </c:forEach>
            </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
