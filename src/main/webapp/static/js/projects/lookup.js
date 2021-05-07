var id = $('#project-delete').val();

$.getJSON('/diary/projects/names', {id : id},
function(data) {
    data.forEach(function(e) {
        let buttonTag = document.createElement('button');
        let buttonText = document.createTextNode(e);

        buttonTag.setAttribute('class', 'form-control-name');
        buttonTag.setAttribute('type', 'button');
        buttonTag.setAttribute('disabled', true);
        buttonTag.append(buttonText);

        $('#member-names').append(buttonTag);
    });
});

$('#project-delete').click((e) => {
    if (!confirm("정말 삭제하시겠습니까?")) return;

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