$(document).ready(function() {

    var numbers = new Bloodhound({
        datumTokenizer: function(d) { return Bloodhound.tokenizers.whitespace(d.politicianName); },
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 1000,
//            remote: "/search/state?state=Michigan",
        prefetch: {
            url: '../JH7_medwards11/search/state?state=Michigan'
        }
    });
    numbers.clearPrefetchCache();
    numbers.initialize();

    var myTypeahead = $('#search .typeahead').typeahead({
            highlight: true
        },
        {
            name: 'politicianSearch',

            displayKey: 'politicianName',
            source: numbers.ttAdapter(),
            templates: {
                header: '<h1 class="state">Michigan</h3>',
                empty: [
                    '<div class="empty-message">',
                    'Name not found.',
                    '</div>'
                ].join('\n'),
                suggestion:Handlebars.compile('<p class="politician-name"><strong>{{politicianName}}</strong> <br> {{party}} (Region {{region}})</p>')
            }
        });

    myTypeahead.on('typeahead:selected',function(obj,datum,name){

        $.ajax ({
                type: "GET",
                url: "../JH7_medwards11/search/politician?state=Michigan&politician=" +  datum['politicianName'],
                dataType: 'json'
            }
        ).then(function(data)
            {
                var photo = data[0].politicianPhoto != null ? data[0].politicianPhoto : "../JH7_medwards11/assets/default-image.png"
                $('#politicianInfo').html('<div class="tooltip" id="politicianPhoto" title="This is a test!" style="background-image: url('+photo+');"/>');
                $('#politicianInfo').append('<button type="button" id="button">&#10006</button>');
                $('#politicianInfo').append('<h1 id="politicianName">'+datum['politicianName']+'</h1>');
                $('#politicianInfo').append('<p id="politicianName">'+data[0].Email+'</p>');
                $('#button').click(function() {
                    $('#searchBar').val('');
                    $("#main").animate({left:'0px'});
                });
                drawTable(data);
                $("#main").animate({left:'250px'});
            });
    });

    $("#searchBar").keyup(function() {
        if(!this.value) {
            console.log("Input got cleared.");
            $("#main").animate({left:'0px'});
        }
    });

});

function drawTable(data) {
    var table = '<table>';
    //add a class that pulls keys...
    var keys = ["Abortion","Corporations","Drugs","Energy_and_Oil","Environment","Foreign_Policy","Free_Trade","Government_Reform","Gun_Control","Health_Care","Homeland_Security","Immigration","Jobs","Social_Security","Tax_Reform","Welfare_and_Poverty"];
    table += "<tr>";
    table += "<td>Party</td>";
    table += "<td>" + data[0]['Party'] + "</td>";
    table += "</tr>";
    for (i = 0; i < keys.length; i++) {
        table += "<tr>";
        table += '<td>'+'<a href="'+keys[i]+':'+data[0][keys[i]]+'">' + keys[i].replace(/_/g," ") + "</a></td>";
        if(data[0][keys[i]] == "Pro") {
            table += '<td style="color:#70FF33">' + data[0][keys[i]] + "</td>";
        } else if (data[0][keys[i]] == "Anti"){
            table += '<td style="color:#FE002D">' + data[0][keys[i]] + "</td>";
        } else {
            table += '<td>' + data[0][keys[i]] + "</td>";
        }
        table += "</tr>";
    }
    $('#politicianInfo').append(table);


}
