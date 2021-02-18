<%--
  Created by IntelliJ IDEA.
  User: coi
  Date: 2/16/21
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign In | DateBook</title>
    <link href="${contextPath}css/login.css" rel="stylesheet">
    <script>

        function init() {
            document.getElementById("error_message_s").value = "";
        }
        window.onload = init;
    </script>
</head>
<body>
    <form id="login_form_id" class="login_form" action="login" method="post" onsubmit="">
        <h1 class="form_title">Sign in</h1>
        <div class="form_grup">
            <input class="form_input" placeholder=" " type="text" name="username">
            <label class="form_label">Username</label>
        </div>
        <div class="form_grup">
            <input class="form_input" placeholder=" " type="password" name="password">
            <label class ="form_label">Password</label>
        </div>

        <p class="error_message" id="error_message_s">${message}</p>

        <button class ="form_button form_login_button" type = "submit">Login</button>
        <button class ="form_button form_register_button" type="button" onclick="location.href='/register'">Sign up</button>
    </form>
</body>
</html>
