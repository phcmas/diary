$('#login-form-withdrawal').click((e) => {
    var name = $('#username-field').val();

    var userdata = {
        name : name,
        password : $('#password-field').val()
    };

    $.ajax({
        type : 'DELETE',
        url : '/diary/user/' + name,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(userdata)
    }).done(function() {
        alert("등록되었습니다. 로그인 한 번 해주세요");
        window.location.href = '/diary/user/signin';
    }).fail(function(error) {
        alert("중복된 이름이 존재합니다.");
    });

});
