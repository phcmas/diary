var memberId = 0;

$('#add-member').click((e) => {
    let buttonTag = document.createElement('button');
    let name = $('#newMember').val();
    let buttonText = document.createTextNode(name);
    let spanTag = document.createElement('span');
    let spanText = document.createTextNode('X');

    buttonTag.setAttribute('class', 'form-control-name');
    buttonTag.setAttribute('name','member-name');
    buttonTag.setAttribute('type', 'button');
    buttonTag.setAttribute('value', name);
    buttonTag.append(buttonText);
    spanTag.setAttribute('class', 'badge badge-secondary');
    spanTag.setAttribute('id', memberId++);
    spanTag.append(spanText);

    buttonTag.append(spanTag);
    $('#member-names').append(buttonTag);
});

$(document).on('click', '.badge', function() {
    var id = $(this).attr('id');
    var upperButton = $('#'+id).closest('button');
    upperButton.remove();
});

$('#btn-save').click((e) => {
    var names = [];
    $('button[name=member-name]').each(function(idx) {
        names.push($(this).val());
    });

    var projectParam = {
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