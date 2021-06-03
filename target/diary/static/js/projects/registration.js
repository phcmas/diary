var memberId = 0;

$('#add-member').click((e) => {addMembers(memberId);});

$(document).on('click', '.badge', function() {
    let id = $(this).attr('id');
    let upperButton = $('#'+id).closest('button');
    upperButton.remove();
});

$('#btn-save').click(function() {
    let names = [];
    $('button[name=member-name]').each(function() {
        names.push($(this).val());
    });

    let projectParam = {
        id : -1,
        title : $('#title').val(),
        startDate : $('#start-date').val(),
        endDate : $('#end-date').val(),
        projectType : $('#project-type').val(),
        situation : $('#situation').val(),
        content : $('#content').val(),
        testScenario : $('#test-scenario').val(),
        names : names
    };

    $.ajax({
        type : 'POST',
        url : '/diary/projects/',
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(projectParam)
    }).done(function() {
        alert("글이 등록되었습니다");
        window.location.href = '/diary/projects/cards';
    }).fail(function(error) {
        alert("글 등록 실패!!");
    });

});
