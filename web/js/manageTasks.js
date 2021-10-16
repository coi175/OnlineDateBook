// get all tasks
// functions which get info about task for edit and edit it
function getAllTasks() {
    let count = 0;
    let successCount = 0;
    $.ajax({
        type: "GET",
        url: "getAllTasks",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function(response) {
            let yesterday = $("#yesterday").children().children(".tasks");
            let today = $("#today").children().children(".tasks");
            let tomorrow = $("#tomorrow").children().children(".tasks");

            yesterday.empty();
            today.empty();
            tomorrow.empty();

            $.each(response, function(index, task) {    // Iterate over the JSON array.
                let now = new Date();
                // if title so long cut it and + "..."
                let taskText = (task.title.length > 45) ? task.title.substr(0, 42) + "..." : task.title;

                // HTML to append
                // usually
                let usually = " <div class=\"row task\">\n" +
                    "                           <div class=\"task_id\">" + task.id + "</div>\n" +
                    "                           <div onclick=\"openShowTaskBlock(this)\" class=\"task_text\">\n" +
                    "                               <p>" + taskText + "</p>\n" +
                    "                           </div>\n" +
                    "                           <div class=\"task_buttons\">\n" +
                    "                               <button onclick=\"openEditTaskBlock(this)\" class=\"task_button\">\n" +
                    "                                   <i class=\"fa fa-pencil\"></i>\n" +
                    "                               </button>\n" +
                    "                               <button onclick=\"markTask(this)\" class=\"task_button\">\n" +
                    "                                   <i class=\"fa fa-check\"></i>\n" +
                    "                               </button>\n" +
                    "                           </div>\n" +
                    "                       </div>";

                // marked

                let marked = "<div  class=\"row task\">\n" +
                    "                           <div class=\"task_id\">" + task.id + "</div>\n" +
                    "                           <div onclick=\"openShowTaskBlock(this)\" class=\"task_text\">\n" +
                    "                               <p class=\"task_text_crossed_out\">" + taskText + "</p>\n" +
                    "                           </div>\n" +
                    "                           <div class=\"task_buttons\">\n" +
                    "                               <button onclick=\"unmarkTask(this)\" class=\"task_button\">\n" +
                    "                                   <i class=\"fa fa-close\"></i>\n" +
                    "                               </button>\n" +
                    "                           </div>\n" +
                    "                       </div>";
                // today

                let s = task.data;
                let date = new Date(Date.parse(s));
                // yesterday
                if (now.getDay() - date.getDay() === 1) {
                    if (task.state === "fault") {
                        count++;
                        yesterday.append("<div  class=\"row task fault_task\">\n" +
                            "                            <div class=\"task_id\">" + task.id + "</div>\n" +
                            "                            <div onclick=\"openShowTaskBlock(this)\" class=\"task_text\">\n" +
                            "                                <p>" + taskText + "</p>\n" +
                            "                           </div>\n" +
                            "                            <div class=\"task_buttons\">\n" +
                            "                                <p><i class=\"fa fa-window-close fault_icon\"></i></p>\n" +
                            "                            </div>\n" +
                            "                        </div>")
                    } else if(task.state === "success") {
                        count++;
                        successCount++;
                        yesterday.append("<div  class=\"row task success_task\">\n" +
                            "                            <div class=\"task_id\">" + task.id + "</div>\n" +
                            "                            <div onclick=\"openShowTaskBlock(this)\" class=\"task_text\">\n" +
                            "                                <p>" + taskText + "</p>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"task_buttons\">\n" +
                            "                                <p><i class=\"fa fa-check-square success_icon\"></i></p>\n" +
                            "                            </div>\n" +
                            "                        </div>")
                    }

                } else if (now.getDay() - date.getDay() === 0) {
                    if(task.state === "usually") {
                        today.append(usually);
                    } else if(task.state === "marked") {
                        today.append(marked);
                    }
                // tomorrow
                } else if(now.getDay() - date.getDay() === -1) {
                    if(task.state === "usually") {
                        tomorrow.append(usually)
                    } else if(task.state === "marked") {
                        tomorrow.append(marked)
                    }
                }
            });

            let part = successCount / count * 100;
            $("#task_progress").val(part);
        },
        error: function(e) {
            console.log("Error");
        }
    });
}
// get all tasks after page loading
getAllTasks();

// function which create a new task
function createNewTask(btn) {
    let title = $("#create_task_title_input");
    let description = $("#create_task_desctiption_input");
    let time = $("#create_task_time_input");
    let day = $("#task_day");

    if (validateFieldsNewTask()) {
        let data = {
            title: title.val(),
            description: description.val(),
            time: time.val(),
            day: day.text()
        };

        $.ajax({
            type: "POST",
            url: "createTask",
            contentType: "application/json", // NOT dataType!
            data: JSON.stringify(data),
            success: function(response) {
                createTaskClose();
                title.val("");
                description.val("");
                time.val("");
                getAllTasks();
            },
            error: function(e) {
                alert("Error! Please try again");
            }
        });
    }
    else {
        alert("Please, fill all fields!");
    }

}


// Fields validation
function validateFieldsNewTask() {
    let tt = $("#create_task_title_input");
    let d = $("#create_task_desctiption_input");
    let dd = $("#task_day");

    if(tt.val() === "" || d.val() === "" || dd.text() === "") {
        return false;
    }
    return true;
}


// functions which get info about task for edit and edit it
function getTaskInfoForEdit(dv) {
    let id = $(dv).parent().parent().children(".task_id").text();

    let data = {id: id};

    $.ajax({
        type: "POST",
        url: "getTask",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function(response) {
            $("#edit_task_id").text(response.id);
            $("#edit_task_title_input").val(response.title);
            $("#edit_task_desctiption_input").val(response.description);
            let time = response.time;
            $("#edit_task_time_input").val(convertTimeTo24(time));
        },
        error: function(e) {
            console.log("Error");
        }
    });
}

function editTask(dv) {
    let id = $("#edit_task_id").text();
    let title = $("#edit_task_title_input");
    let description = $("#edit_task_desctiption_input");
    let time = $("#edit_task_time_input");
    if(validateFieldsEditTask()) {
        let data = {
            id: id,
            title: title.val(),
            description: description.val(),
            time: time.val()
        };

        $.ajax({
            type: "POST",
            url: "editTask",
            contentType: "application/json", // NOT dataType!
            data: JSON.stringify(data),
            success: function(response) {
                editTaskClose();
                getAllTasks();
            },
            error: function(e) {
                alert("Error! Please try again");
            }
        });
    } else {
        alert("Please, fill all fields");
    }
}

function validateFieldsEditTask() {
    let tt = $("#edit_task_title_input");
    let d = $("#edit_task_desctiption_input");
    let dd = $("#edit_task_time_input");

    if(tt.val() === "" || d.val() === "" || dd.val() === "") {
        return false;
    }
    return true;
}

// function which get info about task for show
function getTaskInfoForShowing(dv) {
    let id = $(dv).parent().children(".task_id").text();

    let data = {
        id:id
    }
    $.ajax({
        type: "POST",
        url: "getTask",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function(response) {
            $("#show_task_id").text(response.id);
            $("#show_task_title").text(response.title);
            let s = "<p>" +response.description.split('\n').join('<br>') + "</p>";
            $("#show_task_description").html(s);
            let time = response.time;
            $("#show_task_time").text(time);
        },
        error: function(e) {
            console.log("Error");
        }
    });
}

// delete task
function deleteTask() {
    let id = $("#show_task_id").text();
    let data = {id: id};

    let result = confirm("You want to delete this task, you sure?");
    if (result) {
        $.ajax({
            type: "POST",
            url: "deleteTask",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(data),
            success: function(response) {
                getAllTasks();
                editShowClose();
            },
            error: function(e) {
                console.log("Error");
            }
        });
    }
}

function markTask(dv) {
    let id = $(dv).parent().parent().children(".task_id").text();

    let data = {id: id};

    $.ajax({
        type: "POST",
        url: "markTask",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function(response) {
            getAllTasks();
        },
        error: function(e) {
            console.log("Error");
        }
    });
}

function unmarkTask(dv) {
    let id = $(dv).parent().parent().children(".task_id").text();

    let data = {id: id};

    $.ajax({
        type: "POST",
        url: "unmarkTask",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function(response) {
            getAllTasks();
        },
        error: function(e) {
            console.log("Error");
        }
    });
}

// function to convert time from 12hours format to 24hours
function convertTimeTo24(sTime) {
    let time = sTime.substring(0, 5) + sTime.substring(8, 12);
    let hours = Number(time.match(/^(\d+)/)[1]);
    let minutes = Number(time.match(/:(\d+)/)[1]);
    let AMPM = time.match(/\s(.*)$/)[1];
    if(AMPM === "PM" && hours < 12) hours = hours+12;
    if(AMPM === "AM" && hours === 12) hours = hours-12;
    let sHours = hours.toString();
    let sMinutes = minutes.toString();
    if(hours<10) sHours = "0" + sHours;
    if(minutes<10) sMinutes = "0" + sMinutes;

    return sHours + ":" + sMinutes;
}