<%--
  Created by IntelliJ IDEA.
  User: hehe
  Date: 2020/6/20
  Time: 上午10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.min.js"/>
    <script>
        $(function () {
            $("#username").blur(function () {
                var name = $(this).val();
                $.get("findUsername", {"username": name}, function (data) {
                    alert(data)
                    if (data.userExsit) {
                        $("#s_username").css("color", "red");
                        $("#s_username").html(data.msg);
                    } else {
                        $("#s_username").css("color", "green");
                        $("#s_username").html(data.msg);

                    }


                }, "json")
            })

        })

    </script>
</head>
<body>
<form>
    <input type="text" name="username" placeholder="请输入用户名" id="username">
    <span id="s_username"></span>
    <br><input type="submit" value="注册">

</form>
</body>
</html>
