const date = $('.card-header').val();

$.getJSON('/diary/projects/pagenum', {date : date},
function(data) {
    data.forEach(function(e) {
        let aTag = document.createElement('a');
        let textNode = document.createTextNode(e);
        aTag.setAttribute('href', '/diary/projects/cards?date=' + date + '&start=' + e);
        aTag.setAttribute('class', 'btn btn-secondary');
        aTag.append(textNode);
        $('#page-number').append(aTag);
    });
});
