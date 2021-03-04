<%--
  Created by IntelliJ IDEA.
  User: coi
  Date: 2/15/21
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>DateBook Online</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/index.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  <body>
    <div class="jumbotron my_header">
      <div class="container">
        <div class="row">
          <div class="col">
            <h1>Online Datebook - </h1>
            <h3>Best tool to plan your day</h3>
            <p class="mr-2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quam velit, vulputate eu pharetra
              nec, mattis ac neque. Duis vulputate commodo lectus, ac blandit elit tincidunt id. Sed rhoncus, tortor
              sed eleifend tristique, tortor mauris molestie elit, et lacinia ipsum quam nec dui. Quisque nec mauris sit
              Ut vulputate eros sed felis sodales nec vulputate justo hendrerit. Vivamus varius pretium ligula, a
              aliquam odio euismod sit amet. Quisque laoreet sem sit amet orci ullamcorper at ultricies metus viverra.
              Pellentesque arcu mauris, malesuada quis ornare accumsan, blandit sed diam.</p>

            <button type="button" class="btn btn-primary" onclick="location.href='/home'">Go to DateBook</button>
          </div>
          <div class="col">
            <img class="my_image" src="resources/header_image.jpg" alt="header_image">
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
