const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

//const test = document.getElementById("main-holder");
//var h2 = document.createElement("h2");
//var text = document.createTextNode("aaa");
//h2.appendChild(text);
//test.appendChild(h2);

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    if (username === "user" && password === "web_dev") {
        alert("You have successfully logged in.");
        //location.reload();
    } else {
        alert("dfd");
        //loginErrorMsg.style.opacity = 1;
    }
})