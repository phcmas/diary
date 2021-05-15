const date = $('.card-header').val();
console.log(date);

$.getJSON('/diary/algorithm/pagenum', {date : date},
function(data) {
    data.forEach(function(e) {
        let aTag = document.createElement('a');
        let textNode = document.createTextNode(e);
        aTag.setAttribute('href', '/diary/algorithm/cards?date=' + date + '&start=' + e);
        aTag.setAttribute('class', 'btn btn-secondary');
        aTag.append(textNode);
        $('#page-number').append(aTag);
    });
});
