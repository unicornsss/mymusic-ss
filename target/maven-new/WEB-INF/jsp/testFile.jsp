<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/2
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="/js/jquery.js"></script>
    <script>
        function addMore() {
            var html=$("#files").html();
            var html2=$("#addition").html();
            html2+=html;
            $("#addition").html(html2);
        }</script>
</head>
<body>
<form action="<%=request.getContextPath()%>/test/dotestFile" id="classForm" method="post" enctype="multipart/form-data" >
        文件1<input type="file" name="uploadfile"/>
    文件2<input type="file" name="uploadfile"/>
    文件2<input type="file" name="uploadfile"/>
    <input type="submit"/>
</form>

</body>
</html>
