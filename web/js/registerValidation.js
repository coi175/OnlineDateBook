/**
 * This function checks all fields of form when user try to submit it.
 * @param form_id
 * @returns {boolean}
 */
function validate(form_id) {
    return !!(validate_email(form_id) && validate_username(form_id) && validate_password_match(form_id) && validate_password(form_id));
}

/**
 *  Function for validate e-mail address. It is used in validate() function and in e-mail field in attribute onchange.
 * @param form_id
 * @returns {boolean}
 */
function validate_email(form_id) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var address = document.forms[form_id].elements['email'].value;
    var doc = document.getElementById("email_error");
    if(!reg.test(address)) {
        doc.textContent = "E-mail is invalid"
        return false;
    }
    doc.textContent = "";
    return true;
}

function validate_username(form_id) {
    var reg = /^[a-zA-Z0-9]+$/;
    var username = document.forms[form_id].elements['username'].value;
    var doc = document.getElementById("username_error");
    if(!reg.test(username)) {
        doc.textContent = "Username is invalid";
        return false;
    }
    doc.textContent = "";
    return true;

}

function validate_password(form_id) {
    var reg = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
    var password = document.forms[form_id].elements['password'].value;
    var doc =   document.getElementById("password_error");
    if(!reg.test(password)) {
        doc.textContent = "Password is invalid. It must have at least 8 symbols";
        return false;
    }
    doc.textContent = "";
    return true;
}

function validate_password_match(form_id) {
    var password = document.forms[form_id].elements['password'].value;
    var repeat_password = document.forms[form_id].elements['repeat_password'].value;
    var doc = document.getElementById("password_match_error");
    if(password != repeat_password) {
        doc.textContent = "Passwords don't match";
        return false;
    }
    doc.textContent = "";
    return true;
}

