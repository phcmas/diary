var authenticate = function() {
    var userdata = "name=" + $('#username-field').val() + "&password=" + $('#password-field').val();

    $.ajax({
        type : 'POST',
        url : '/diary/user/authenticate',
        datatype : 'json',
        data : userdata
    }).done(function() {
        alert("환영합니다.");
        window.location.href = '/diary/main';
    }).fail(function() {
        alert("존재하지 않은 사용자입니다.");
    });
};

$('#login-form-submit').click(function() {
    authenticate();
});

$('#password-field').keydown(function(key){
    if (key.keyCode == 13) authenticate();
});

$('#login-form-signup').click(function() {
    window.location.href = '/diary/signup';
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
