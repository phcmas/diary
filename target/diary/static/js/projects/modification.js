var memberId = 0;

function makeOptionTag(id, value, text) {
    let optionTag = document.createElement('option');
    let textNode = document.createTextNode(text);

    $('#'+id).remove();
    optionTag.setAttribute('id', id);
    optionTag.setAttribute('value', value);
    optionTag.selected = true;
    optionTag.append(textNode);
    return optionTag;
}

function showOption() {
    let type = $('#previous-type').val();
    let optionTag;

    switch (type) {
        case "기능개발":
            optionTag = makeOptionTag('function-development', 'FUNCTION_DEVELOPMENT','기능개발');
            $('#previous-type').after(optionTag);
            break;
        case "성능개선":
            optionTag = makeOptionTag('performance-improvement', 'PERFORMANCE_IMPROVEMENT','성능개선');
            $('#function-development').after(optionTag);
            break;
        case "오류해결":
            optionTag = makeOptionTag('error-resolution', 'ERROR_RESOLUTION', '오류해결');
            $('#performance-improvement').after(optionTag);
            break;
        default:
            console.log("Error");
    }
}

function showMembers() {
    let id = $('#btn-update').val();
    $.getJSON('/diary/projects/' + id + '/names',
        function(names) {
            names.forEach(function(e) {
                let buttonTag = document.createElement('button');
                let buttonText = document.createTextNode(e);
                let spanTag = document.createElement('span');
                let spanText = document.createTextNode('X');

                buttonTag.setAttribute('class', 'form-control-name');
                buttonTag.setAttribute('name','member-name');
                buttonTag.setAttribute('type', 'button');
                buttonTag.setAttribute('value', e);
                buttonTag.append(buttonText);
                spanTag.setAttribute('class', 'badge badge-secondary');
                spanTag.setAttribute('id', memberId++);
                spanTag.append(spanText);

                buttonTag.append(spanTag);
                $('#member-names').append(buttonTag);
            });
    });
};

$(document).ready(function() {
    showOption();
    showMembers();
});

$('#add-member').click((e) => {addMembers(memberId);});

$(document).on('click', '.badge', function() {
    let id = $(this).attr('id');
    let upperButton = $('#'+id).closest('button');
    upperButton.remove();
});

$('#btn-update').click(function() {
    let names = [];
    let id = $('#btn-update').val();

    $('button[name=member-name]').each(function(){
        names.push($(this).val());
    });

    let projectData = {
        id : id,
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
        type : 'PUT',
        url : '/diary/projects/' + id,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(projectData)
    }).done(function() {
        alert("글이 수정되었습니다");
        window.location.href = '/diary/projects/cards';
    }).fail(function(error) {
        alert("글 등록 실패!!");
    });

});