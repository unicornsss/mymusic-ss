<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/10/30
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--<html>--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/admin_addspe.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(document).ready(function() {
//      $(function(){
            var MaxInputs = 25;
            var father = $("#add-singer-mes");
            var AddButton = $("#addbtn"); //Add button ID
//        var Delbtn = $("#delbtn");
            var FieldCount=1;
            var html ='<tr class="song-list"><td></td><td> <div >歌名<input type="text" name="songName" required="required"/>mp3文件 <input type="file" name="file1" required="required" value="mp3文件"/>图片<input type="file" value="歌曲图片" class="" name="file2" accept="image/png, image/jpeg, image/jpg" required="required"/> <input type="button" class="delbtn" value="- "/> </div> </td> </tr>';
//        var x = 0;
            $(AddButton).click(function(e){
                if(MaxInputs >= FieldCount) //max input box allowed
                {
                    FieldCount++;
                    $(father).append(html);
                }
                return false;
            });
            $(father).on("click",".delbtn",function(){
                //        alert('OK');
                if(FieldCount > 1){
                    $(this).parent().parent().parent().remove();
                    FieldCount--;
                }
                return false;
            });
        });
        function changepic() {
            var reads= new FileReader();
            f=document.getElementById('uploadfile').files[0];
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
            <li></li>
        </ul>
    </div>
    <div id="show-message">
        <!--<div id="add-singer"><a href="">新增歌手</a></div>-->
        <form action="<%=request.getContextPath()%>/admin/doAddSpe" method="post" enctype="multipart/form-data">
            <table id = "add-singer-mes">
                <tr><th class="show-name">资料</th>
                    <th class="show-username"></th></tr>
                <tr><td >专辑名</td>
                    <td><input type="text" name="specialName" required="required"/></td></tr>
                <tr><td >发行公司</td>
                    <td><input type="text" name="specialCompany" required="required"/></td></tr>
                <tr><td >歌手</td>
                    <td><select id="" name="singerId" required="required">
                        <c:forEach items="${singerList}" var="singerList">
                            <option value="${singerList.id}">${singerList.singerName}</option>
                        </c:forEach>
                    </select>
                    </td></tr>
                <tr><td>地区</td>
                    <td><select id="singer-country" name="specialArea" required="required">
                        <option value="x">请选择</option>
                        <option value="内地">内地</option><option value="港台">港台</option><option value="欧美">欧美</option><option value="日本">日本</option><option value="韩国">韩国</option><option value="其他">其他</option>
                    </select>
                    </td></tr>
                <tr><td >语种</td>
                    <td><select id="singer-sex" name="specialLanguage" required="required">
                        <option value="x">请选择</option>
                        <option value="中文">中文</option><option value="英语">英语</option><option value="韩语">韩语</option><option value="日语">日语</option><option value="法语">法语</option><option value="西班牙语">西班牙语</option><option value="泰语">泰语</option><option value="德语">德语</option><option value="意大利语">意大利语</option>
                        <option value="粤语">粤语</option>
                        <option value="其他">其他</option>
                    </select>
                    </td></tr>
                <tr><td >类别</td>
                    <td><select id="singer-style" name="specialStyle" required="required">
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
                    <td><textarea type="text" name="specialSummary" id="singer-Summary"> </textarea></td></tr>
                <tr><td>照片</td>
                    <td>
                        <img src="" alt="" class="show-shoppic" id="showpic"/>
                        <input type="file" class="" name="filePic" id="uploadfile" onchange="changepic(this)"  accept="image/png, image/jpeg, image/jpg" required="required"/>
                    </td></tr>
                <tr><td>操作</td><td><input type="submit" value="确认添加专辑"/></td></tr>
                <tr><td>歌曲添加</td><td><div class="add-songs-but"><input type="button" id="addbtn"  value="+"/>
                    <!--<input id="delbtn"  type="button" value="- "/>-->
                </div></td></tr>
                <tr class="song-list"><td></td>
                    <td>
                        <div >歌名<input type="text" name="songName" required="required"/>mp3文件 <input type="file" name="file1" required="required" value="mp3文件"/>
                            图片<input type="file" value="歌曲图片" class="" name="file2" accept="image/png, image/jpeg, image/jpg" required="required"/>
                            <input type="button" class="delbtn" value="- "/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>