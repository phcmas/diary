$('#signup').click((e) => {
    if (hasEmptyInfo()) return;

    let userdata = {
        name : $('#username').val(),
        password : $('#password').val()
    };

    $.ajax({
        type : 'POST',
        url : '/diary/user/',
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
