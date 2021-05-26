function deleteFile() {
    let fileId = $('#file-download').val();

    $.ajax({
        type : 'DELETE',
        url : '/diary/algorithm/file/' + fileId
    }).done(function() {
        alert('알고리즘이 삭제되었습니다');
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
};

$('#btn-delete').click(function() {
    let algorithmId = $('#btn-delete').val();

    if (!confirm("정말 삭제하시겠습니까?")) return;

    $.ajax({
        type : 'DELETE',
        url : '/diary/algorithm/' + algorithmId
    }).done(function() {
        deleteFile();
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
});