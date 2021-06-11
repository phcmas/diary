function authenticate(name, password, func) {
    var userdata = "name=" + $('#username').val() + "&password=" + $('#password').val();

    $.ajax({
        type : 'POST',
        url : '/diary/user/authenticate',
        datatype : 'json',
        data : userdata
    }).done(function() {
        func(name);
    }).fail(function() {
        alert("잘못된 정보를 입력했습니다");
    });
};


function hasEmptyInfo() {
    let name = $('#username').val();
    let password = $('#password').val();

    if (name === "") {
        alert("이름을 입력해주세요");
        return true;
    } else if (password === "") {
        alert("패스워드를 입력해주세요");
        return true;
    }

    return false;
};