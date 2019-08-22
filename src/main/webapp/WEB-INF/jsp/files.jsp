<%@ page import="java.util.Arrays" %>
<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/3
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title></title>
</head>
<body>
<h3>okok</h3>
<div>
    ${fileshow}
    <c:forEach items="${alltext}" var="alltext">
        ${alltext}
    </c:forEach>
        <h3>hello</h3>
    <c:forEach items="${str}" var="str">
        ${str}
    </c:forEach>
</div>
</body>
</html>
