$('#project-save').click((e) => {
    var projectData = {
        id : -1,
        title : $('#title').val(),
        startDate : $('#start-date').val(),
        endDate : $('#end-date').val(),
        projectType : $('#project-type').val(),
        situation : $('#situation').val(),
        content : $('#content').val(),
        testScenario : $('#test-scenario').val()
    };

    $.ajax({
        type : 'POST',
        url : '/diary/projects/',
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(projectData)
    }).done(function() {
        alert("글이 등록되었습니다");
        window.location.href = '/diary/projects/cards';
    }).fail(function(error) {
        alert("글 등록 실패!!");
    });

});