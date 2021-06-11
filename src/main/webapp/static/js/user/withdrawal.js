function deleteUser(name) {
    $.ajax({
        type : 'DELETE',
        url : '/diary/user/' + name,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function(e) {
        alert('삭제되었습니다');
        window.location.href = '/diary/user/signin';
    }).fail(function(error) {
        alert("삭제 실패!");
    });
};

$('#withdrawal').click((e) => {
    if (hasEmptyInfo()) return;
    let name = $('#username').val();
    let password = $('#password').val();
    authenticate(name, password, deleteUser);
});
