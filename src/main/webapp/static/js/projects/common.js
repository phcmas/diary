function addMembers(memberId) {
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
};

function stringToDate(str) {
    let sYear = str.substring(0,4);
    let sMonth = str.substring(5,7);
    let sDay = str.substring(8,10);
    return new Date(sYear, Number(sMonth)-1, sDay);
};

function hasEmptyInfo() {
    let now = new Date();
    let startDate = stringToDate($('#start-date').val());
    let endDate = stringToDate($('#end-date').val());
    let title = $('#title').val();
    let projectType = $('#project-type').val();
    let situation = $('#situation').val();
    let content = $('#content').val();
    let testScenario = $('#test-scenario').val();
    let names = $('button[name=member-name]').val();

    if (title === "") {
        alert("제목을 입력해주세요");
        return true;
    } else if (startDate === "") {
        alert("시작일을 입력해주세요") ;
        return true;
    } else if (endDate === "") {
        alert("완료일을 입력해주세요");
        return true;
    } else if (startDate.getTime() > now.getTime()) {
        alert("시작일을 현재 시간 이전으로 해주세요 ");
        return true;
    } else if (startDate.getTime() > endDate.getTime()) {
        alert("완료일을 시작일 이후로 해주세요");
        return true;
    } else if (projectType === "0") {
        alert("종류를 입력해주세요");
        return true;
    } else if (names === undefined) {
        alert("참여 멤버를 입력해주세요");
        return true;
    }

    return false;
};