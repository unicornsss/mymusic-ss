<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/10/30
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/css/admin_addsinger.css"/>
    <script>
        function changepic3() {
            var reads= new FileReader();
            f=document.getElementById('uploadfile1').files[0];
            reads.readAsDataURL(f);
            reads.onload=function (e) {
                document.getElementById('showpic').src=this.result;
            };
        }
    </script>
</head>
<body>
<div id="all">
    <div id="manager-top"><div id="manager-name">管理者后台</div><div id="manager-out"><a href="">退出</a></div></div>
    <div id="show-list">
        <ul id="list-menu">
            <li><div><a href="/admin/showSinger">歌手管理</a></div></li><br/>
            <li><div><a href="/admin/showAllSongs">歌曲管理</a></div></li><br/>
            <li><div><a href="/admin/showSpe">专辑管理</a></div></li><br/>
            <li><div><a href="">歌曲管理</a></div></li><br/>
            <%--<li></li>--%>
        </ul>
    </div>
    <div id="show-message">
        <div id="add-singer"><a href="">新增歌手</a></div>
        <form action="<%=request.getContextPath()%>/admin/doAddSinger" method="post" enctype="multipart/form-data" charset=utf-8" >
            <table id = "add-singer-mes">
                <tr><th class="show-name">资料</th>
                    <th class="show-username"></th></tr>
                <tr><td >歌手名</td>
                    <td><input type="text" name="singerName" required="required"/></td></tr>
                <tr><td >国籍</td>
                    <td><select id="singer-country" name="singerCountry" required="required">
                        <option value="x">请选择</option>
                        <option value="内地">内地</option><option value="港台">港台</option><option value="欧美">欧美</option><option value="日本">日本</option><option value="韩国">韩国</option><option value="其他">其他</option>
                    </select>
                    </td></tr>
                <tr><td >男/女/组合</td>
                    <td><select id="singer-sex" name="singerSex" required="required">
                        <option value="x">请选择</option>
                        <option value="男">男</option><option value="女">女</option><option value="组合">组合</option>
                    </select>
                    </td></tr>
                <tr><td >类别</td>
                    <td><select id="singer-style" name="singerStyle" required="required">
                        <option >请选择</option>
                        <option value="流行">流行</option><option value="嘻哈">嘻哈</option>
                        <option value="摇滚">摇滚</option><option value="电子">电子</option>
                        <option value="民谣">民谣</option> <option value="R&B">R&B</option>
                        <option value="民歌">民歌</option> <option value="轻音乐">轻音乐</option>
                        <option value="爵士">爵士</option> <option value="古典">古典</option>
                        <option value="乡村">乡村</option> <option value="蓝调">蓝调</option>
                    </select>
                    </td></tr>
                <tr><td>描述信息</td>
                    <td><textarea type="text" name="singerSummary" id="singer-message" ></textarea></td></tr>
                <tr><td>照片</td>
                    <td>
                        <img src="" alt="" class="show-shoppic" id="showpic"/>
                        <input type="file" class="" name="file1" id="uploadfile1" onchange="changepic3(this)"  accept="image/png, image/jpeg, image/jpg" required="required"/>
                    </td></tr>
                <tr><td>操作</td><td><input type="submit" value="确认添加"/></td></tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
