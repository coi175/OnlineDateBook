<%--
  Created by IntelliJ IDEA.
  User: coi
  Date: 2/15/21
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home | DateBook Online</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/home.css">
</head>
<body>
   <div class="container">
       <nav class="navbar">
           <h1>Your date book</h1>
           <div>Настройки аккаунта</div>
       </nav>
       <div class="row card_container">
            <div id="yesterday" class="col">
                <div class="card">
                    <h3>Yesterday</h3>
                    <div class="tasks col">

                    </div>
                    <progress value="50" max="100"></progress>
                </div>
            </div>
           <div id="today" class="col">
               <div class="card">
                   <h3>Today</h3>
                   <div class="tasks col">

                   </div>
                   <button class="add_task_button">Add task</button>
               </div>
           </div>
           <div id="tomorrow" class="col">
               <div class="card">z
                   <h3>Tomorrow</h3>
                   <div class="tasks col">

                   </div>
                   <button class="add_task_button">Add task</button>
               </div>
           </div>
       </div>
       <div class="row card_switcher">
           <div class="col card_switcher_container">
               <button class="change_button to_yesterday"></button>
               <button class="change_button to_today"></button>
               <button class="change_button to_tomorrow"></button>
           </div>
       </div>

       <div class="task_screen_container">
           <div class="task_screen">
               что-то там
           </div>
       </div>

       <div class="task_screen_container">
           <div class="task_screen">
               что-то там
           </div>
       </div>

       <div class="task_screen_container">
           <div class="edit_task_screen">
               что-то там
           </div>
       </div>
   </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
