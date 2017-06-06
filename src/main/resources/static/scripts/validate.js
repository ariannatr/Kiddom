/**
 * Created by eleni on 06-Jun-17.
 */

function validate() {
    var password = document.getElementById("password").value;
    var password_conf = document.getElementById("passwordsignup_confirm").value;
    if (password != password_conf) {
        document.getElementById("to_koumpi").disabled = true;
        document.getElementById("to_koumpi").innerHTML = "Wrong password!";
    } else {
        document.getElementById("to_koumpi").disabled = false;
        document.getElementById("to_koumpi").innerHTML = "Get Started";
    }
}