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

function deleteAlgorithm(id) {
    $.ajax({
        type : 'DELETE',
        url : '/diary/algorithm/' + id
    }).done(function(e) {
        deleteFile();
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
};

$('#btn-update').click(function() {
    let id = $('#btn-delete').val();

    $.ajax({
        type : 'GET',
        url : '/diary/algorithm/' + id + '/authority'
    }).done(function(e) {
        if (e === true) {
            window.location.href='/diary/algorithm/' + id + '/modification';
        } else {
            alert("수정 권한이 없습니다");
        }
    });
});

$('#btn-delete').click(function() {
    let id = $('#btn-delete').val();

    $.ajax({
        type : 'GET',
        url : '/diary/algorithm/' + id + '/authority'
    }).done(function(e) {
        if (e === true && confirm("정말 삭제하시겠습니까?")) {
            deleteAlgorithm(id);
        } else if (e === false) {
            alert("삭제 권한이 없습니다");
        }
    });
});

