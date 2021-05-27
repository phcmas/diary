function authenticate(name, password, func) {
    var userdata = "name=" + $('#username').val() + "&password=" + $('#password').val();

    $.ajax({
        type : 'POST',
        url : '/diary/user/authenticate',
        datatype : 'json',
        data : userdata
    }).done(function() {
        window.location.href = '/diary/main';
    }).fail(function() {
        alert('인증 실패!');
    });
};
