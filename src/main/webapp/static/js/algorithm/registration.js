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
        alert("알고리즘이 등록되었습니다.");
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert("파일 등록 실패");
    });
};

function addFileName() {
    let fileName = $('#file-upload').val();
    let str = fileName.split('\\');
    let newLabel = document.createElement('label');
    let newText = document.createTextNode(str[str.length-1]);

    newLabel.setAttribute('class', 'custom-file-label');
    newLabel.setAttribute('id', 'file-name');
    newLabel.append(newText);

    $('#file-name').remove();
    $('#code-file').append(newLabel);
};

$(document).ready(function() {
    $('#file-upload').on("change", addFileName);
});

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
