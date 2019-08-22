/**
 * Created by Administrator on 2018/10/27.
 */
$(function () {
    check($("#number"), $("#number_check"), $("#submit"));
    check($("#password"), $("#password_check"), $("#submit"));

    $("#number").bind("input propertychange change",function(event){
        check($("#number"), $("#number_check"), $("#submit"));
    });
    $("#password").bind("input propertychange change",function(event){
        check($("#password"), $("#password_check"), $("#submit"));
    });
});
function check(label, check, sub) {
    if(label.val() == "") {
        check.html("账号或密码不能为空!");
        sub.attr("disabled", true);
    } else {
        check.html("");
        sub.attr("disabled", false);
    }
}
