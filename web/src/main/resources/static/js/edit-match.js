$(document).ready( function () {


    $(".home-club-name").html(teste.homeClub.name);
    $(".visitor-club-name").html(teste.visitorClub.name);

    var idHome = teste.homeClub.identificator;
    var idVisitor = teste.visitorClub.identificator;

    console.log( idHome + " " + idVisitor);
    $("#home-badge").html('<img src="http://localhost:8080/clubs/' + idHome +'/badge"/>');
    $("#visitor-badge").html('<img src="http://localhost:8080/clubs/' + idVisitor +'/badge"/>');


});
