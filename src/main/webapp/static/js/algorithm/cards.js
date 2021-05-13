const year = $('.card-header').val();
console.log(year);

//$.getJSON('/diary/algorithm/pagenum', {year : year, month : month},
//function(data) {
//    data.forEach(function(e) {
//        let aTag = document.createElement('a');
//        let textNode = document.createTextNode(e);
//        aTag.setAttribute('href', '/diary/algorithm/cards?year=' + year + '&month=' + month + '&start=' + e);
//        aTag.setAttribute('class', 'btn btn-secondary');
//        aTag.append(textNode);
//        $('#page-number').append(aTag);
//    });
//});
