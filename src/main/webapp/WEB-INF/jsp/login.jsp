<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录jsp</title>
</head>
<body>
<h3>表单登录</h3>
<table>
    <tr>
        <td>用户名:</td>
        <td><input type="text" autocomplete="off" name="username"></td>
    </tr>
    <tr>
        <td>密码:</td>
        <td><input type="password" autocomplete="off" name="password"></td>
    </tr>
    <tr>
        <td>记住我:</td>
        <td><input type="checkbox" name="remember-me"></td>
    </tr>
    <tr>
        <td colspan="2">
            <button type="button" onclick="login()">登录</button>
        </td>
    </tr>
</table>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    function login() {
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        //自动登录字段的 name 必须是 remember-me
        var rememberMe = $("input[name=remember-me]").val();
        if (username === "" || password === "") {
            alert("用户名或密码不能为空");
            return;
        }

        //参数必须是username，password
        $.ajax({
            type: "POST",
            url: "authentication/form1",
            data: {
                "username": username,
                "password": password,
                "remember-me": rememberMe
            },
            success: function (e) {
                alert("登陆成功")
                setTimeout(function () {
                    location.href = 'hello';
                }, 1500);
            },
            error: function (e,a,b) {
                console.log(e.responseText);
                alert("登陆失败")
            }
        });
    }

</script>
</body>
</html>

