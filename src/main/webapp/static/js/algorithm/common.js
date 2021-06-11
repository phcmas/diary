function stringToDate(str) {
    let sYear = str.substring(0,4);
    let sMonth = str.substring(5,7);
    let sDay = str.substring(8,10);
    return new Date(sYear, Number(sMonth)-1, sDay);
};

function hasEmptyInfo() {
    let now = new Date();
    let solvedDate = stringToDate($('#solved-date').val());
    let title = $('#title').val();
    let source = $('#source').val();
    let type = $('#type').val();
    let language = $('#language').val();
    let difficulty = $('#difficulty').val();

    if (title === "") {
        alert("제목을 입력하세요");
        return true;
    } else if (solvedDate === "") {
        alert("푼 날짜를 입력하세요");
        return true;
    } else if (solvedDate.getTime() > now.getTime()) {
        alert("푼 날짜를 현재 시간 이전으로 해주세요");
        return true;
    } else if (source === "0") {
        alert("출처를 입력하세요");
        return true;
    } else if (type === "0") {
        alert("종류를 입력하세요");
        return true;
    } else if (language === "0") {
        alert("언어를 입력하세요");
        return true;
    } else if (difficulty === "0") {
        alert("난이도를 입력하세요");
        return true;
    }

    return false;
};
