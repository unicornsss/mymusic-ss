<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/10/30
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
    <title>管理员--注册</title>
    <link rel="stylesheet" href="/css/admin_reg.css"/>
    <script src="/js/jquery.min.js"></script>
    <%--<script src="/js/admin_manage.js"></script>--%>
    <script type="text/javascript" src="/js/admin_register.js"></script>
</head>
<body>
<div class="wrapper">
    <form action="<%=request.getContextPath()%>/admin/doRegister" method="post">
        <div class="loginBox">
            <div class="loginBoxCenter">
                <p><label for="adminname">用户名：</label></p>
                <!--autofocus 规定当页面加载时按钮应当自动地获得焦点。 -->
                <!-- placeholder提供可描述输入字段预期值的提示信息-->
                <p><input type="text" id="adminname" name="administratorNumber" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入账号" value="" /></p>
                <!-- required 规定必需在提交之前填写输入字段-->
                <p><label for="password">密码：(6-20位以字母开头只能包含字母、数字、下划线)</label></p>
                <p><input type="password" id="password" name="password" class="loginInput" required="required" placeholder="请输入密码" value="" onblur="checkPwd(this.value)"/>
                    <label style="color:red;">*</label>
                </p>
                <p><font id="passwordMSG"></font></p>
                <%--oninput="checkPwd2(this.value)"        oninput="checkPwd2(this.value)--%>
                <p><label for="repassword">重复密码：</label></p>
                <p><input type="password" id="repassword" name="repassword" class="loginInput" required="required" placeholder="请输入密码" value="" onblur="checkPwd2(this.value)" />
                    <label style="color:red;"> *</label> </p>
                <p><font id="mm2MSG"></font></p>

                <input id="remember" type="checkbox" name="remember" />
                <%--<label for="remember">记住登录状态</label>--%>
            </div>
            <div class="loginBoxButtons">
                <button class="loginBtn"><input type="submit" value="提交"/></button>
                <%--<a href=""><div> 新用户注册</div></a>--%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
