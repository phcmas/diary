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
    }).done(function() {
        alert("알고리즘이 등록되었습니다");
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert("알고리즘 등록 실패!!");
    });

});