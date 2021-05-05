const year = $('.card-header').val();

$.getJSON('/diary/projects/pagenum', {year: year},
function(data) {
    data.forEach(function(e) {
        let aTag = document.createElement('a');
        let textNode = document.createTextNode(e);
        aTag.setAttribute('href', '/diary/projects/cards?year=' + year + '&start=' + e);
        aTag.setAttribute('class', 'btn btn-secondary');
        aTag.append(textNode);
        $('#page-number').append(aTag);
    });
});
