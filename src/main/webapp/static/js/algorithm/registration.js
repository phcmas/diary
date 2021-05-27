var isFileUploaded = false;

function addCodeFile(algorithmId) {
    let codeFile = $('#code-file')[0];
    let formData = new FormData(codeFile);
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

function addAlgorithm() {
    let algorithmParam = {
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
        if (isFileUploaded === true) addCodeFile(id);
    });
};

function addContent() {
    // 파일 이름 중복에 대해 더 나은 방법을 고민해보아야함.
    // 임시로 이렇게 구현함
    if (isFileUploaded === false) {
        addAlgorithm();
        alert("알고리즘이 등록되었습니다.");
        window.location.href = '/diary/algorithm/cards';
        return;
    }

    let fileName = $('#file-upload').val();
    let str = fileName.split('\\');
    let name = str[str.length-1];

    $.getJSON('/diary/algorithm/file/overlap/' + name,
        function(e) {
            if (e === false) addAlgorithm();
            else alert("중복된 파일이 존재합니다");
        }
    );
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
    isFileUploaded = true;
};

$(document).ready(function() {
    $('#file-upload').on("change", addFileName);
});

$('#btn-save').click(function() {
    addContent();
});
