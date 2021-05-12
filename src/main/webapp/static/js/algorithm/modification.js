var id = $('#btn-update').val();

$('#btn-update').click((e) => {
    var algorithmParam = {
        id : id,
        title : $('#title').val(),
        solvedDate : $('#solved-date').val(),
        source : $('#source').val(),
        type : $('#type').val(),
        language : $('#language').val(),
        difficulty : $('#difficulty').val(),
        explanation : $('#explanation').val(),
        content : $('#content').val()
    };

    $.ajax({
        type : 'PUT',
        url : '/diary/algorithm/' + id,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(algorithmParam)
    }).done(function() {
        alert("알고리즘이 수정되었습니다");
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert("수정 실패!!");
    });

});