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
    let id = $(dv).parent().parent().children(".task_id").text();

    $.ajax({
        type: "POST",
        url: "getTask",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function(response) {

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