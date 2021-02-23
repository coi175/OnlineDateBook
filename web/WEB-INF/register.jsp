<%--
  Created by IntelliJ IDEA.
  User: coi
  Date: 2/16/21
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up | DateBook</title>
    <link rel="stylesheet" href="../css/register.css" type="text/css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script src="../js/registerValidation.js"></script>
    <script>
        $(document).on("submit", "#register_form_id", function(event) {
            var $form = $(this);

            $.post($form.attr("action"), $form.serialize(), function(response) {
                if(response === "Success") {
                    window.location.href='/login';
                }
                else {
                    $("#error_message_register").text(response);
                }
            });

            event.preventDefault(); // Important! Prevents submitting the form.
        });
    </script>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="register_form_id" class="register_form" action="register" method="post" onsubmit="">
    <h1 class="form_title">Sign up</h1>
    <div class="form_group">
        <input class="form_input" placeholder=" " type="text" name="username" onchange="validate_username('register_form_id')">
        <span id="username_error" class="field_error_message"></span>
        <label class="form_label">Username</label>
    </div>
    <div class="form_group">
        <input class="form_input" placeholder=" " type="text" name="email" onchange="validate_email('register_form_id')">
        <span id="email_error" class="field_error_message"></span>
        <label class="form_label">E-mail</label>
    </div>
    <div class="form_group">
        <input class="form_input" placeholder=" " type="password" name="password" onchange="validate_password('register_form_id')">
        <span id="password_error" class="field_error_message"></span>
        <label class ="form_label">Password</label>
    </div>
    <div class="form_group">
        <input class="form_input" placeholder=" " type="password" name="repeat_password" onchange="validate_password_match('register_form_id')">
        <span id="password_match_error" class="field_error_message"></span>
        <label class ="form_label">Repeat Password</label>
    </div>

    <p id = "error_message_register" class="error_message"></p>
    <button class ="form_button" type = "submit">Create Account</button>
</form>
</body>
</html>