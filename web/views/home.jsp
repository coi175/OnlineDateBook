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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="../js/openBlocks.js"></script>
    <script src="../js/manageTasks.js"></script>
</head>
<body>
   <div class="container">
       <nav class="navbar">
           <h1>Your date book</h1>
           <div>Настройки аккаунта</div>
       </nav>
       <div class="row card_container">
           <!-- YESTERDAY -->
            <div id="yesterday" class="col">
                <div class="card">
                    <h3>Yesterday</h3>
                    <div class="tasks col">
                        <div class="row task fault_task">
                            <div class="task_id">

                            </div>
                            <div class="task_text">
                                <p>текст sdfssd sdf sdfsdf sf ddsfsdlksdkfjsdklfjsdk</p>
                            </div>
                            <div class="task_buttons">
                                <p><i class="fa fa-window-close fault_icon"></i></p>
                            </div>
                        </div>

                        <div class="row task success_task">
                            <div class="task_id">

                            </div>
                            <div class="task_text">
                                <p>текст sdfssd sdf sdfsdf sf ddsfsdlksdkfjsdklfjsdk</p>
                            </div>
                            <div class="task_buttons">
                                <p><i class="fa fa-check-square success_icon"></i></p>
                            </div>
                        </div>
                    </div>

                    <progress value="50" max="100"></progress>
                </div>
            </div>

           <!-- TODAY -->
           <div id="today" class="col">
               <div class="card">
                   <h3>Today</h3>
                   <div class="tasks col">
                       <div class="row task">
                           <div class="task_id">1</div>
                           <div class="task_text">
                               <p>текст sdfssd sdf sdfsdf sf ddsfsdlksdkfjsdklfjsdk</p>
                           </div>
                           <div class="task_buttons">
                               <button onclick="openEditTaskBlock(this)" class="task_button">
                                   <i class="fa fa-pencil"></i>
                               </button>
                               <button class="task_button">
                                   <i class="fa fa-check"></i>
                               </button>
                           </div>
                       </div>

                       <div class="row task">
                           <div class="task_id">2</div>
                           <div class="task_text">
                               <p class="task_text_crossed_out">текст sdfssd sdf sdfsdf sf ddsfsdlksdkfjsdklfjsdk</p>
                           </div>
                           <div class="task_buttons">
                               <button class="task_button">
                                   <i class="fa fa-close"></i>
                               </button>
                           </div>
                       </div>
                   </div>
                   <button class="add_task_button" onclick="openCreateTaskBlockToday(this)">Add task</button>
               </div>
           </div>

           <!-- TOMORROW -->
           <div id="tomorrow" class="col">
               <div class="card">
                   <h3>Tomorrow</h3>
                   <div class="tasks col">

                   </div>
                   <button class="add_task_button" onclick="openCreateTaskBlockTomorrow(this)">Add task</button>
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

       <!-- CREATE TASK BLOCK -->
       <div id="create_task_block" class="task_screen_container row">
           <div class="task_screen col">
               <div class="close_button_block mb-2">
                   <button class="create_task_close_button" onclick="createTaskClose()"><i class="fa fa-close task_block_close_icon"></i></button>
               </div>
               <div class="row mb-2">
                   <label for="create_task_title_input">Task title:</label>
                   <input type="text" id="create_task_title_input" class="task_title_input">
               </div>
               <div class="row text_area_block mb-2">
                   <label for="create_task_desctiption_input">Task description: <br></label>
                   <textarea maxlength="1000" id="create_task_desctiption_input" class="task_description_input"></textarea>
               </div>
               <div class="row mb-4">
                   <label for="create_task_time_input">Task time:</label>
                   <input type="time" id="create_task_time_input" class="task_time_input" name="time" required/>
               </div>
               <div class="row d-flex">
                   <button class="add_task_button" onclick="createNewTask()">Add task</button>
               </div>
               <div id="task_day"></div>
           </div>
       </div>

       <!-- EDIT TASK BLOCK-->
       <div id="edit_task_block" class="task_screen_container">
           <div class="task_screen col">
               <div class="close_button_block mb-2">
                   <button class="create_task_close_button" onclick="editTaskClose()"><i class="fa fa-close task_block_close_icon"></i></button>
               </div>
               <div class="row mb-2">
                   <label for="create_task_title_input">Task title:</label>
                   <input type="text" id="edit_task_title_input" class="task_title_input">
               </div>
               <div class="row text_area_block mb-2">
                   <label for="create_task_desctiption_input">Task description: <br></label>
                   <textarea maxlength="1000" id="edit_task_desctiption_input" class="task_description_input"></textarea>
               </div>
               <div class="row mb-4">
                   <label for="edit_task_time_input">Task time:</label>
                   <input type="time" id="edit_task_time_input" name="time" class="task_time_input"/>
               </div>
               <div class="row d-flex">
                   <button class="add_task_button" onclick="editTask(this)">Edit task</button>
               </div>
               <div id="edit_task_id"></div>
           </div>
       </div>

       <!-- SHOW TASK BLOCK -->
       <div id="show_task_block" class="task_screen_container">
           <div class="edit_task_screen">
               что-то там
           </div>
       </div>
   </div>

    <div id="layer"></div>
    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
