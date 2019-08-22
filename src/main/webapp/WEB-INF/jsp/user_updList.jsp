<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/19
  Time: 8:27
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
            $(".every-song").mouseenter(function(){
                if(btnFlag ==0){
//                    $(".btn-play").css('display','block');
                    $(this).children(".every-header").children(".btn-play").css('display','block');
                    $(this).children(".do-list-del").css('display','block');
//                .children(".every-spe").
                    btnFlag = 1;
                }
            });
            $(".every-song").mouseleave(function(){
//                $(".btn-play").css('display','none');
                $(this).children(".every-header").children(".btn-play").css('display','none');
                $(this).children(".do-list-del").css('display','none');
//                children(".every-spe").
                btnFlag = 0;
            });
            $(".topa a").click(function(){
                $(this).css("background",'#35ffaa');
            });
            var noticeFlag = 0;
            $("#singer-notice-btt").click(function(){
//                if(noticeFlag == 0){
//                没有关注
                if($(this).html() =="收藏" ){
                    $(this).html("已收藏");
                    noticeFlag ++;
                    $.ajax({
                        url:"/music/noticeSpeAdd",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{speId: $(this).attr('property') },
                        success:function(data)
                        {
                            if(data.result =="addSuccess"){
                                console.log("收藏成功"+data.result);
                            }else{
                                console.log("收藏失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("收藏失败");
                        }
                    });
//                    alert($(this).attr('id'));
                }else{
                    $(this).html("收藏");
                    noticeFlag --;
                    $.ajax({
                        url:"/music/noticeSpeSub",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{speId:$(this).attr('property')},
                        success:function(data){
                            if(data.result =="SubSuccess"){
                                console.log("取消收藏成功"+data.result);
                            }else{console.log("取消失败")}}
                    });
                }
            });
            $(".do-list-del").click(function () {
                $(this).parent().parent().remove();
                $.ajax({
                    url:"/music/deleteListSong",
                    type: 'post',
                    dataType: 'json',
                    data:{songId: $(this).attr('property') ,
                        listId: ${songList.id}
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
            });
            var checkID = [];
            $("#select-type").change(function(){
                $("input[name='test']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                    checkID[i] =$(this).val();
                });
            });
            var show_select = true;
            $("#select-type").mouseleave(function(){
                if(checkID.length != 0){
                    if(show_select){
                        for (var i  = 0;i < 3;i++) {
                            if (checkID[i] != null) {
                                $("#show-btn-type").children("div").append('<span style="width: 65px;height: 20px;background: #b8eeff;display: inline-block; margin-right: 5px;">' + checkID[i] + '</span>');
                                show_select = false;
                            }}
                        $.ajax({
                            url:"/music/updateListType",
                            type: 'post',
                            dataType: 'json',
                            data:{listId: ${songList.id}, types: checkID},
                            traditional:true ,
                            success:function(data) {
                                if(data.result =="updateSuccess"){
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
                    }
                }
//        if(checkID.length == 0){
////          alert("true");
////          为空
//        }else{
            });

            $("#select-type").mouseenter(function(){
                show_select = true;
                $("#show-btn-type").children("div").children("span").remove();
//        $(this).children().filter("span").remove();
            });
            if($(":checkbox").size()>=3){
                $(":checkbox").removeAttr("checked");
                $(":checkbox").attr("disabled","disabled");
                $(":checkbox").removeAttr("disabled");
            }
            var num = 0;
            $(":checkbox").each(function(){
                $(this).click(function(){
                    if($(this)[0].checked) {
//            alert($(this)[0]);
                        ++num;
                        if(num == 3) {
                            //alert("最多选择 三项 的上限已满, 其他选项将会变为不可选.");
                            $(":checkbox").each(function(){
                                if(!$(this)[0].checked) {
                                    $(this).attr("disabled", "disabled");
                                }
                            });
                        }
                    } else {
                        -- num;
                        if( num <= 2 ) {
                            $(":checkbox").each(function(){
                                if(!$(this)[0].checked) {
                                    $(this).removeAttr("disabled");
                                }
                            });
                        }
                    }
                });
            });

            var selectFlag = true;
            $("#btn1").click(function(){
                if(selectFlag){
                    $("#select-type").css("display","block");
                    $(":checkbox").attr("checked",false);
                    $(":checkbox").removeAttr("disabled");
                    selectFlag = false;
                }else{
                    $("#select-type").css("display","none")
                    selectFlag = true;
                }
            });

//            $("#singer-de-mes").blur(function(){
//                $(this).change(function(){
//                    alert($(this).val());
//                });
//            });
            $("#singer-de-mes").bind("input propertychange", function(){
//                alert($(this).val());
                $.ajax({
                    url:"/music/updateListSum",
                    type: 'post',
                    dataType: 'json',
                    data:{listId: ${songList.id}, summary: $(this).val()},
//                    traditional:true ,
                    success:function(data) {
                        if(data.result =="updateSuccess"){
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

            $(".singer-play-btt").click(function(){
                var songBtnId = $(this).attr('property');
                var a ='<%=session.getAttribute("result")%>';
                if(a=="null"||a==""){
                    alert("blank");
                    window.open("/music/play?songId="+songBtnId,"_blank");
                }else{
                    alert("not_blank");
                    $.ajax({
                        url:"/music/play",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{songId: songBtnId},
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

        });
    </script>
    <style>
        .types {width: 645px;}
        #select-type {position:absolute;top:300px; left:200px; z-index:2;background: #ffffff; opacity: 0.9;display: none;}
        .dif-type{width: 65px;height: 20px;background: #b8eeff;display: inline-block; margin-right: 5px;}
    </style>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="/other/logo.png" alt=""/></li>
        <li class="topa" id="top-color"><a href=""><span>音乐馆</span></a></li>
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
        <li><span id="select-btn2"><a href="">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="show-sin">
        <div id="every-singer-show">
            <%--<img src="/files/" alt=""/>--%>
            <div id="singer-details" style="">
                <span id="singer-de-name">${songList.listName}</span><br/>
                <span>创建者：${userShow.username}</span><br/>
                <%--<span>语种：${special.specialLanguage}</span>--%>
                <span>类型：</span>
                <span>
                    <div id="show-btn-type">
                        <div>
                            <c:if test="${not empty types}">
                            <c:forEach items="${types}" var="types">
                                <span class="dif-type">${types}</span>
                            </c:forEach>
                        </c:if>
                        </div>
                        <button id="btn1" style="width: 120px;height: 40px; margin-top: 10px; margin-bottom: 5px;">修改类型</button></div>
                </span>
                <div id="select-type">
                    <span>语种</span>
                    <div class="types">
                        <input type="checkbox" name="test" class="check" value="国语"/>国语
                        <input type="checkbox" name="test" class="check"  value="英语"/>英语
                        <input type="checkbox" name="test" class="check"  value="韩语"/>韩语
                        <input type="checkbox" name="test" class="check"  value="粤语"/>粤语
                        <input type="checkbox" name="test" class="check"  value="日语"/>日语
                        <input type="checkbox" name="test" class="check"  value="小语种"/>小语种
                        <input type="checkbox" name="test" class="check"  value="闽南语"/>闽南语
                        <input type="checkbox" name="test" class="check"  value="法语"/>法语
                        <input type="checkbox" name="test" class="check"  value="拉丁语"/>拉丁语
                    </div>
                    <span>流派</span>
                    <div class="types">
                        <input type="checkbox" name="test" class="check" value="流行"/>流行
                        <input type="checkbox" name="test" class="check"  value="轻音乐"/>轻音乐
                        <input type="checkbox" name="test" class="check"  value="摇滚"/>摇滚
                        <input type="checkbox" name="test" class="check"  value="民谣"/>民谣
                        <input type="checkbox" name="test" class="check"  value="R&amp;B"/>R&amp;B
                        <input type="checkbox" name="test" class="check"  value="嘻哈"/>嘻哈
                        <input type="checkbox" name="test" class="check"  value="电子"/>电子
                        <input type="checkbox" name="test" class="check"  value="古典"/>古典
                        <input type="checkbox" name="test" class="check"  value="乡村"/>乡村
                        <input type="checkbox" name="test" class="check"  value="蓝调"/>蓝调
                        <input type="checkbox" name="test" class="check"  value="爵士"/>爵士
                        <input type="checkbox" name="test" class="check"  value="拉丁"/>拉丁
                        <input type="checkbox" name="test" class="check"  value="中国传统"/>中国传统
                        <input type="checkbox" name="test" class="check"  value="世界音乐"/>世界音乐
                    </div>
                    <span>主题</span>
                    <div class="types">
                        <input type="checkbox" name="test" class="check" value="ACG"/>ACG
                        <input type="checkbox" name="test" class="check"  value="经典"/>经典
                        <input type="checkbox" name="test" class="check"  value="网络歌曲"/>网络歌曲
                        <input type="checkbox" name="test" class="check"  value="影视"/>影视
                        <input type="checkbox" name="test" class="check"  value="KTV热歌"/>KTV热歌
                        <input type="checkbox" name="test" class="check"  value="儿歌"/>儿歌
                        <input type="checkbox" name="test" class="check"  value="中国风"/>中国风
                        <input type="checkbox" name="test" class="check"  value="古风"/>古风
                        <input type="checkbox" name="test" class="check"  value="情歌"/>情歌
                        <input type="checkbox" name="test" class="check"  value="城市"/>城市
                        <input type="checkbox" name="test" class="check"  value="现场音乐"/>现场音乐
                        <input type="checkbox" name="test" class="check"  value="背景音乐"/>背景音乐
                        <input type="checkbox" name="test" class="check"  value="佛教音乐"/>佛教音乐
                        <input type="checkbox" name="test" class="check"  value="UP主"/>UP主
                        <input type="checkbox" name="test" class="check"  value="乐器"/>乐器
                        <input type="checkbox" name="test" class="check"  value="DJ"/>DJ
                    </div>
                    <span>心情</span>
                    <div class="types"><input type="checkbox" name="test" class="check" value="伤感"/>伤感
                        <input type="checkbox" name="test" class="check"  value="安静"/>安静
                        <input type="checkbox" name="test" class="check"  value="快乐"/>快乐
                        <input type="checkbox" name="test" class="check"  value="治愈"/>治愈
                        <input type="checkbox" name="test" class="check"  value="励志"/>励志
                        <input type="checkbox" name="test" class="check"  value="甜蜜"/>甜蜜
                        <input type="checkbox" name="test" class="check"  value="寂寞"/>寂寞
                        <input type="checkbox" name="test" class="check"  value="宣泄"/>宣泄
                        <input type="checkbox" name="test" class="check"  value="思念"/>思念</div>
                    <span>场景</span>
                    <div class="types">
                        <input type="checkbox" name="test" class="check" value="睡前"/>睡前
                        <input type="checkbox" name="test" class="check"  value="夜店"/>夜店
                        <input type="checkbox" name="test" class="check"  value="学习"/>学习
                        <input type="checkbox" name="test" class="check"  value="运动"/>运动
                        <input type="checkbox" name="test" class="check"  value="开车"/>开车
                        <input type="checkbox" name="test" class="check"  value="约会"/>约会
                        <input type="checkbox" name="test" class="check"  value="工作"/>工作
                        <input type="checkbox" name="test" class="check"  value="旅行"/>旅行
                        <input type="checkbox" name="test" class="check"  value="派对"/>派对
                        <input type="checkbox" name="test" class="check"  value="婚礼"/>婚礼
                        <input type="checkbox" name="test" class="check"  value="咖啡馆"/>咖啡馆
                    </div>
                </div>
                <input id="singer-de-mes" type="text" value="${songList.listSummary}" name="listSummary"/>
                <br/>
            </div>
        </div>
    </div>
    <div id="show-song">
        <ul id="song-menu">
            <li id="header-song">歌曲</li>
            <li id="header-singer">歌手</li>
            <li id="header-spe">专辑</li>
            <li id="header-time"></li>
        </ul>
        <ul class="list-song">
            <c:if test="${not empty allSong}">
                <c:forEach items="${allSong}" var="songs">
                    <li>
                        <div class="every-song">
                            <div class="every-header">
                                <a href="" class="song-name"><span>${songs.songName}</span></a>
                                <div class="btn-play">
                                    <%--<a href=""><button>播放</button></a>--%>
                                     <button class="singer-play-btt" property="${songs.id}">播放</button>
                                    <select class="btn-list" ><option value="1" >添加到</option><option value="222">222</option></select>
                                </div>
                            </div>
                            <span class="every-singer"><a href="">${songs.singer.singerName}</a></span>
                            <span class="every-spe">
                                ${songs.special.specialName}
                            </span>
                            <%--<span class="every-song-do" style="width: 80px;height: 60px;background: #D1D1D1;">--%>
                                <button class="do-list-del" property="${songs.id}" style="float: right;display: none;width: 47px;height: 40px;">删除</button>
                            <%--</span>--%>
                        </div>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
