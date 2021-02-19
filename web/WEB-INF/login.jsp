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
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script>
        $(document).on("submit", "#login_form_id", function(event) {
            var $form = $(this);

            $.post($form.attr("action"), $form.serialize(), function(response) {
                if(response === "Success") {
                    window.location.href='/home';
                }
                else {
                    $("#error_message_login").text(response);
                }
            });

            event.preventDefault(); // Important! Prevents submitting the form.
        });
    </script>
</head>
<body>
    <form id="login_form_id" class="login_form" action="login">
        <h1 class="form_title">Sign in</h1>
        <div class="form_group">
            <input id="login_username" class="form_input" placeholder=" " type="text" name="username">
            <label class="form_label">Username</label>
        </div>
        <div class="form_group">
            <input id="login_password"  class="form_input" placeholder=" " type="password" name="password">
            <label class ="form_label">Password</label>
        </div>

        <p id="error_message_login" class="error_message"></p>

        <button class ="form_button form_login_button" type = "submit">Login</button>
        <button class ="form_button form_register_button" type="button" onclick="location.href='/register'">Sign up</button>
    </form>
</body>
</html>
