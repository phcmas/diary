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

function hasEmptyInfo() {
    let title = $('#title').val();
    let startDate = $('#start-date').val();
    let endDate = $('#end-date').val();
    let projectType = $('#project-type').val();
    let situation = $('#situation').val();
    let content = $('#content').val();
    let testScenario = $('#test-scenario').val();
    let names = $('button[name=member-name]').val();

    if (title === "") {
        alert("제목을 입력해주세요");
        return true;
    } else if (startDate === "") {
        alert("시작 날짜를 입력해주세요") ;
        return true;
    } else if (endDate === "") {
        alert("완료 날짜를 입력해주세요");
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