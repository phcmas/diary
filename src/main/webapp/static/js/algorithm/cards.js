$(document).ready(function() {
    let date = $('.card-header').val();

    $.getJSON('/diary/algorithm/pagenum/' + date,
        function(nums) {
            nums.forEach(function(e) {
                let aTag = document.createElement('a');
                let textNode = document.createTextNode(e);
                aTag.setAttribute('href', '/diary/algorithm/cards?date=' + date + '&start=' + e);
                aTag.setAttribute('class', 'btn btn-secondary');
                aTag.append(textNode);
                $('#page-number').append(aTag);
        });
    });
});
