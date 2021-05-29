$(document).ready(function() {
    let id = $('#btn-delete').val();

    $.getJSON('/diary/projects/' + id + '/names',
    function(names) {
        names.forEach(function(name) {
            let buttonTag = document.createElement('button');
            let buttonText = document.createTextNode(name);

            buttonTag.setAttribute('class', 'form-control-name');
            buttonTag.setAttribute('type', 'button');
            buttonTag.setAttribute('disabled', true);
            buttonTag.append(buttonText);

            $('#member-names').append(buttonTag);
        });
    });
});

function deleteProject(id) {
    $.ajax({
        type : 'DELETE',
        url : '/diary/projects/' + id,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function() {
        alert('글이 삭제되었습니다');
        window.location.href = '/diary/projects/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
};

$('#btn-update').click(function() {
    let id = $('#btn-delete').val();

    $.ajax({
        type : 'GET',
        url : '/diary/projects/' + id + '/authority',
    }).done(function(e) {
        if (e === true) {
            window.location.href='/diary/projects/' + id + '/modification';
        } else {
            alert("수정 권한이 없습니다");
        }
    });
});

$('#btn-delete').click(function() {
    let id = $('#btn-delete').val();

    $.ajax({
        type : 'GET',
        url : '/diary/projects/' + id + '/authority',
    }).done(function(e) {
        if (e === true && confirm("정말 삭제하시겠습니까?")) {
            deleteProject(id);
        } else if (e === false) {
            alert("삭제 권한이 없습니다");
        }
    });
});
