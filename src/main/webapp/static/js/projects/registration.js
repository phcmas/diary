$('#add-member').click((e) => {
    var prev = $('#members').val();
    var newMember = $('#newMember').val();
    $('#members').val(prev + ', '+ newMember);
});


$('#project-save').click((e) => {
    var members = [];

    $('input[name=members]').each(function(idx) {
        var value = $(this).val();
        members.push(value);
    });

    var projectData = {
        id : -1,
        title : $('#title').val(),
        startDate : $('#start-date').val(),
        endDate : $('#end-date').val(),
        projectType : $('#project-type').val(),
        content : $('#content').val(),
        testScenario : $('#test-scenario').val(),
        members: members
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