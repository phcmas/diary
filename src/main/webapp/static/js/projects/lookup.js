$('#project-delete').click((e) => {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    var id = $('#project-delete').val();

    $.ajax({
        type : 'DELETE',
        url : '/diary/projects/' + id,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function() {
        alert('글이 삭제되었습니다');
        window.location.href = '/diary/projects/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });

});