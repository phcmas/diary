function windowChange(name) {
    alert(name + "님 환영합니다");
    window.location.href = '/diary/main';
};

function goToMain() {
    var name = $('#username').val();
    var password = $('#password').val();
    authenticate(name, password, windowChange);
};

$('#submit').click(function() {
    goToMain();
});

$('#password').keydown(function(key) {
    if (key.keyCode == 13) goToMain();
});

$('#signup').click(function() {
    window.location.href = '/diary/user/signup';
});

$('#withdrawal').click(function() {
    window.location.href = '/diary/user/withdrawal';
});

//    e.preventDefault();
//const loginForm = document.getElementById("login-form");
//const loginButton = document.getElementById("login-form-submit");
//const loginErrorMsg = document.getElementById("login-error-msg");

//const test = document.getElementById("main-holder");
//var h2 = document.createElement("h2");
//var text = document.createTextNode("aaa");
//h2.appendChild(text);
//test.appendChild(h2);

//e.preventDefault();
//const name = $('#login-form [name="name"]').val();
//const password = $('#login-form [name="password"]').val();

//if (name === "user" && password === "web") {
//    alert("개발자 다이어리에 오신걸 환영합니다!");
//    window.location.href = '/diary/main';
//} else {
//    alert("id 또는 password를 잘못 입력하셨습니다");
//}
