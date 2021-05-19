var algorithmId = $('#btn-update').val();

function updateFile() {
    var codeFile = $('#code-file')[0];
    var formData = new FormData(codeFile);
    var fileId = $('#btn-cancel').val();

    formData.append("id", fileId);

    $.ajax({
        type : 'PUT',
        method : 'PUT',
        url : '/diary/algorithm/file',
        data : formData,
        contentType : false,
        processData : false
    }).done(function() {
        alert("알고리즘이 수정되었습니다");
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

$('#btn-update').click((e) => {
    var algorithmParam = {
        id : algorithmId,
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
        url : '/diary/algorithm/' + algorithmId,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(algorithmParam)
    }).done(function() {
        alert("알고리즘이 수정되었습니다");
        window.location.href = '/diary/algorithm/cards';
        //updateFile();
    }).fail(function(error) {
        alert("수정 실패!!");
    });

});