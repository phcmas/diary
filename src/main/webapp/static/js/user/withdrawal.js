function deleteUser(name) {
    $.ajax({
        type : 'DELETE',
        url : '/diary/user/' + name,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function(e) {
        if (e > 0) {
            alert('삭제되었습니다');
            window.location.href = '/diary/user/signin';
        } else {
            alert('존재하지 않는 유저입니다');
        }
    }).fail(function(error) {
        alert("실패했습니다");
    });
};

$('#withdrawal').click((e) => {
    var name = $('#username').val();
    var password = $('#password').val();
    authenticate(name, password, deleteUser);
});
