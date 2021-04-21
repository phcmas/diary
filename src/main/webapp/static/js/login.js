//const loginForm = document.getElementById("login-form");
//const loginButton = document.getElementById("login-form-submit");
//const loginErrorMsg = document.getElementById("login-error-msg");

//const test = document.getElementById("main-holder");
//var h2 = document.createElement("h2");
//var text = document.createTextNode("aaa");
//h2.appendChild(text);
//test.appendChild(h2);

$('#login-form-submit').on("click", (e) => {
    e.preventDefault();
    const username = $('#login-form [name="username"]').val();
    const password = $('#login-form [name="password"]').val();

    if (username === "user" && password === "web") {
        alert("개발자 다이어리에 오신걸 환영합니다!");
        window.location.href = '/reservation/index';
    } else {
        alert("id 또는 password를 잘못 입력하셨습니다");
    }
})
