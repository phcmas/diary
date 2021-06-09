var isFileUpdated = 0;

function addFileName() {
    let fileName = $('#file-upload').val();
    let str = fileName.split('\\');
    let newLabel = document.createElement('label');
    let newText = document.createTextNode(str[str.length-1]);

    newLabel.setAttribute('class', 'custom-file-label');
    newLabel.setAttribute('id', 'file-name');
    newLabel.append(newText);

    $('#file-name').remove();
    $('#code-file').append(newLabel);
    isFileUpdated = 1;
};

function makeOptionTag(id, value, text) {
    let optionTag = document.createElement('option');
    let textNode = document.createTextNode(text);

    $('#'+id).remove();
    optionTag.setAttribute('id', id);
    optionTag.setAttribute('value', value);
    optionTag.selected = true;
    optionTag.append(textNode);
    return optionTag;
};

function changeSourceTag() {
    let source = $('#source').val();
    let optionTag;

    switch (source) {
        case "LeetCode":
            optionTag = makeOptionTag('leetcode', 'LeetCode', 'LeetCode');
            $('#prev-source').after(optionTag);
            break;
        case "AlgoSpot":
            optionTag = makeOptionTag('algospot', 'AlgoSpot', 'AlgoSpot');
            $('#leetcode').after(optionTag);
            break;
        case "Programmers":
            optionTag = makeOptionTag('programmers', 'Programmers', 'Programmers');
            $('#algospot').after(optionTag);
            break;
        case "HackerRank":
            optionTag = makeOptionTag('hacker-rank', 'HackerRank', 'HackerRank');
            $('#programmers').after(optionTag);
            break;
        case "ETC":
            optionTag = makeOptionTag('etc', 'ETC', 'ETC');
            $('#hacker-rank').after(optionTag);
            break;
        default:
            console.log("Error");
    }

    $('#prev-source').val('0');
};

function changeType() {
    let type = $('#type').val();
    let optionTag;

    switch (type) {
        case "DP":
            optionTag = makeOptionTag('dp','DP','DP');
            $('#prev-type').after(optionTag);
            break;
        case "DFS":
            optionTag = makeOptionTag('dfs','DFS','DFS');
            $('#dp').after(optionTag);
            break;
        case "BFS":
            optionTag = makeOptionTag('bfs','BFS','BFS');
            $('#dfs').after(optionTag);
            break;
        case "Greedy":
            optionTag = makeOptionTag('greedy','GREEDY','Greedy');
            $('#bfs').after(optionTag);
            break;
        case "Two Pointer":
            optionTag = makeOptionTag('two-pointer','TWO_POINTER','Two Pointer');
            $('#greedy').after(optionTag);
            break;
        case "Hash Table":
            optionTag = makeOptionTag('hash-table','HASH_TABLE','Hash Table');
            $('#two-pointer').after(optionTag);
            break;
        case "Heap":
            optionTag = makeOptionTag('heap','HEAP','Heap');
            $('#hash-table').after(optionTag);
            break;
        case "Union Find":
            optionTag = makeOptionTag('union-find','UNION_FIND','Union Find');
            $('#heap').after(optionTag);
            break;
        case "ETC":
            optionTag = makeOptionTag('etc','ETC','ETC');
            $('#union-find').after(optionTag);
            break;
        default:
            console.log("Error");
    }

    $('#prev-type').val('0');
};

function changeLanguage() {
    let language = $('#language').val();
    let optionTag;

    switch (language) {
        case "C":
            optionTag = makeOptionTag('c', 'C', 'C');
            $('#prev-language').after(optionTag);
            break;
        case "C++":
            optionTag = makeOptionTag('cpp', 'CPP', 'C++');
            $('#c').after(optionTag);
            break;
        case "Java":
            optionTag = makeOptionTag('java', 'JAVA', 'Java');
            $('#cpp').after(optionTag);
            break;
        case "JavaScript":
            optionTag = makeOptionTag('java-script', 'JAVA_SCRIPT', 'JavaScript');
            $('#java').after(optionTag);
            break;
        default:
            console.log("Error");
    }

    $('#prev-language').val('0');
};

function changeDifficulty() {
    let difficulty = $('#difficulty').val();
    let optionTag;

    switch (difficulty) {
        case "Easy":
            optionTag = makeOptionTag('easy', 'EASY', 'Easy');
            $('#prev-difficulty').after(optionTag);
            break;
        case "Medium":
            optionTag = makeOptionTag('medium', 'MEDIUM', 'Medium');
            $('#easy').after(optionTag);
            break;
        case "Hard":
            optionTag = makeOptionTag('hard', 'HARD', 'Hard');
            $('#medium').after(optionTag);
            break;
        default:
            console.log("Error");
    }

    $('#prev-difficulty').val('0');
};

function updateFile() {
    let codeFile = $('#code-file')[0];
    let formData = new FormData(codeFile);
    let algorithmId = $('#btn-update').val();
    let fileId = $('#btn-cancel').val();

    formData.append("id", fileId);
    formData.append("algorithmId", algorithmId);

    $.ajax({
        type : 'PUT',
        method : 'PUT',
        url : '/diary/algorithm/file',
        data : formData,
        contentType : false,
        processData : false,
    }).done(function(e) {
        alert("알고리즘이 수정되었습니다");
        window.location.href = '/diary/algorithm/cards';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
};

$(document).ready(function() {
    changeSourceTag();
    changeType();
    changeLanguage();
    changeDifficulty();
});

$('#file-upload').on("change", addFileName);

$('#btn-update').click((e) => {
    if (hasEmptyInfo()) return;

    let algorithmId = $('#btn-update').val();
    let algorithmParam = {
        id : algorithmId,
        title : $('#title').val(),
        solvedDate : $('#solved-date').val(),
        source : $('#source').val(),
        type : $('#type').val(),
        language : $('#language').val(),
        difficulty : $('#difficulty').val(),
        explanation : $('#explanation').val(),
        content : $('#content').val()
    };

    $.ajax({
        type : 'PUT',
        url : '/diary/algorithm/' + algorithmId,
        datatype : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(algorithmParam)
    }).done(function() {
        if (isFileUpdated == 1) {
            updateFile();
        } else {
            alert('알고리즘이 수정되었습니다');
            window.location.href = '/diary/algorithm/cards';
        }
    }).fail(function(error) {
        alert("수정 실패!!");
    });

});
