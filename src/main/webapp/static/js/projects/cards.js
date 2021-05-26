function makePageNumber() {
    var date = $('.card-header').val();

    $.getJSON('/diary/projects/pagenum/' + date,
    function(nums) {
        nums.forEach(function(e) {
            let aTag = document.createElement('a');
            let textNode = document.createTextNode(e);
            aTag.setAttribute('href', '/diary/projects/cards?date=' + date + '&start=' + e);
            aTag.setAttribute('class', 'btn btn-secondary');
            aTag.append(textNode);
            $('#page-number').append(aTag);
        });
    });
};

function makeNumberOfMembers() {

};

$(document).ready(function() {
    makePageNumber();
    makeNumberOfMembers();
});
