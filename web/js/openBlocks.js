// create task block
function openCreateTaskBlockToday() {
    $("#create_task_block").show();
    $("#layer").show();
    $("#task_day").text("today");
}

function openCreateTaskBlockTomorrow() {
    $("#create_task_block").show();
    $("#layer").show();
    $("#task_day").text("tomorrow");
}

function createTaskClose() {
    $("#create_task_block").hide();
    $("#layer").hide();
}

//edit task block
function openEditTaskBlock(dv) {
    $("#edit_task_block").show();
    $("#layer").show();
    getTaskInfoForEdit(dv);
}

function editTaskClose() {
    $("#edit_task_block").hide();
    $("#layer").hide();
}

// show task block
function openShowTaskBlock(dv) {
    $("#show_task_block").show();
    $("#layer").show();
    getTaskInfoForShowing(dv);
}

function editShowClose() {
    $("#show_task_block").hide();
    $("#layer").hide();
}