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
