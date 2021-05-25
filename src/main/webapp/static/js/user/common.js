function authenticate(name, password, func) {
    var userdata = "name=" + $('#username').val() + "&password=" + $('#password').val();

    $.ajax({
        type : 'POST',
        url : '/diary/user/authenticate',
        datatype : 'json',
        data : userdata
    }).done(function(e) {
        console.log(e);
        func(name);
    }).fail(function() {
        alert('인증 실패!');
    });
};
