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

$('#btn-delete').click(function() {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    let id = $('#btn-delete').val();

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
});
