//var formData = new FormData();
//
//var addCodeFile = function (e) {
//    var file = e.target.files[0];
//    formData.append("file", file);
//};

//$(document).ready(function() {
//    $('#file-upload').on("change", addCodeFile);
//});

function addCodeFile(algorithmId) {
    var codeFile = $('#code-file')[0];
    var formData = new FormData(codeFile);
    formData.append("algorithmId", algorithmId);

    $.ajax({
        type : 'POST',
        method : 'POST',
        url : '/diary/algorithm/file',
        data : formData,
        contentType : false,
        processData : false
    }).done(function(e) {
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert("파일 등록 실패");
    });
};

$('#btn-save').click((e) => {
    var algorithmParam = {
        id : -1,
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
        type : 'POST',
        url : '/diary/algorithm/',
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(algorithmParam)
    }).done(function(id) {
        addCodeFile(id);
    }).fail(function(error) {
        alert("알고리즘 등록 실패!!");
    });

});