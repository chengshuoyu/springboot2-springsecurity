<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>登陆成功</h1>
<button onclick="check(this)" data-href="java">检测java角色</button>
<button onclick="check(this)" data-href="docker">检测docker角色</button>
<button onclick="check(this)" data-href="php">检测php角色</button>
<button onclick="check(this)" data-href="custom">检测自定义匹配器</button>
<a href="logout">退出登录</a>
</body>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    function check(e) {
        var url = e.dataset.href;
        $.ajax({
            type: "POST",
            url: url,
            success: function (e) {
                alert(e)
            },
            error: function (e, a, b) {
                console.log(e);
                alert("没有权限")
            }
        });
    }
</script>
</html>

