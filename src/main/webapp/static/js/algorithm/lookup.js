var id = $('#btn-delete').val();

$('#btn-delete').click((e) => {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    $.ajax({
        type : 'DELETE',
        url : '/diary/algorithm/' + id,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function() {
        alert('알고리즘이 삭제되었습니다');
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });

});