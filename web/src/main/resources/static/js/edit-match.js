var matchId = "";

var homeClubName = "";
var homeClubId = "";

var visitorClubName = "";
var visitorClubId = "";

/*Lista dos jogadores*/
var clubs = {
    "HOMECLUB": {
        allPlayers: []
    },
    "VISITORCLUB": {
        allPlayers: []
    }
};

var CLUBTOBEEDITED = {
    HOMECLUB: "HOMECLUB",
    VISITORCLUB: "VISITORCLUB"
};

var PLAYERSTATUS = {
    RELATED: "RELATED",
    STARTING: "STARTING",
    SUBSTITUTE: "SUBSTITUTE"
};


$(document).ready(function () {

    $("#stating-players-home-selector").click(selectHomeClubStartingPlayers);
    $("#substitute-players-home-selector").click(selectHomeClubSubstitutePlayers);

    $("#stating-players-visitor-selector").click(selectVisitorClubStartingPlayers);
    $("#substitute-players-visitor-selector").click(selectVisitorClubSubstitutePlayers);

    $('#selectPlayersModal').on('show.bs.modal', function () {

        $(".chosen-select").chosen({
            max_selected_options: 11,
            width: "100%"
        });
    });


    $('#selectPlayersModal').on('hidden.bs.modal', function () {
        $('.chosen-select option').prop('selected', false).trigger('chosen:updated');
    });


    matchId = matchLoaded.identificator;

    homeClubId = matchLoaded.homeClub.identificator;
    homeClubName = matchLoaded.homeClub.name;

    visitorClubId = matchLoaded.visitorClub.identificator;
    visitorClubName = matchLoaded.visitorClub.name;

    loadClubsBadges();
    getPlayersFromClubs();

    $(".home-club-name").html(homeClubName);
    $(".visitor-club-name").html(visitorClubName);

});

function loadClubsBadges() {

    console.log(matchId + ": " + homeClubId + " " + visitorClubId);
    $("#home-badge").html('<img src="http://localhost:8080/clubs/' + homeClubId + '/badge"/>');
    $("#visitor-badge").html('<img src="http://localhost:8080/clubs/' + visitorClubId + '/badge"/>');
}

function getPlayersFromClubs() {

    var urlHomeClub = "/clubs/" + homeClubId + "/players";
    $.get(urlHomeClub, function (data) {
        homeClubAllPlayers = buildLookup(data);
        clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers = homeClubAllPlayers;
    });

    var urlHomeClub = "/clubs/" + visitorClubId + "/players";
    $.get(urlHomeClub, function (data) {
        visitorClubAllPlayers = buildLookup(data);
        clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers = visitorClubAllPlayers;
    });
}

function buildLookup(data) {

    var lookup = {};
    for (var i = 0; i < data.length; i++) {
        var playerId = data[i].identificator;
        var player = data[i];
        player["status"] = PLAYERSTATUS.RELATED;
        lookup[playerId] = player;
    }
    return lookup;
}

function populateSelect(CLUBTOBEEDITED, isSelectingStartingPlayers) {

    var options = "";

    $('#modal-player-select').html('');

    for (playerId in clubs[CLUBTOBEEDITED].allPlayers) {

        if (clubs[CLUBTOBEEDITED].allPlayers.hasOwnProperty(playerId)) {

            var playerTemp = clubs[CLUBTOBEEDITED].allPlayers[playerId];
            var playerName = playerTemp.displayName;
            var playerNumber = playerTemp.number;

            /*Verifica se o jogador já foi selecionado*/
            if ((isSelectingStartingPlayers && playerTemp["status"] == PLAYERSTATUS.STARTING) ||
                !isSelectingStartingPlayers && playerTemp["status"] == PLAYERSTATUS.SUBSTITUTE) {

                options += '<option value="' + playerId + '" selected>' + playerNumber + " - " + playerName + '</option>';

            } else if (playerTemp["status"] == PLAYERSTATUS.RELATED ){
                options += '<option value="' + playerId + '" >' + playerNumber + " - " + playerName + '</option>';
            } else {
                options += '<option value="' + playerId + '" disabled>' + playerNumber + " - " + playerName + '</option>';
            }
        }
    }

    $("#modal-player-select").html(options);
    $(".chosen-select").trigger('chosen:updated');
}

function selectHomeClubStartingPlayers() {

    $(".modal-title").html("Selecione os 11 jogadores titulares do " + homeClubName);

    populateSelect(CLUBTOBEEDITED.HOMECLUB, true);

    $("#btn-modal-save").unbind("click");
    $("#btn-modal-save").click(function () {
        saveStartingPlayers(CLUBTOBEEDITED.HOMECLUB);
    });


    $(".chosen-select").unbind('change');
    $(".chosen-select").on('change', function(evt, params) {

        var editedPlayerId = "";
        if ( params.hasOwnProperty('selected') ){
            editedPlayerId = params['selected'];
            clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.STARTING;

        } else if (params.hasOwnProperty('deselected') ) {
            editedPlayerId = params['deselected'];
            clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.RELATED;
        }
    });

    $('#selectPlayersModal').modal('show');
}


function selectHomeClubSubstitutePlayers() {
    console.log("selectHomeClubSubstitutePlayers");
    $(".modal-title").html("Selecione os jogadores reservas do " + homeClubName);

    populateSelect(CLUBTOBEEDITED.HOMECLUB, false);

    $("#btn-modal-save").unbind("click");
    $("#btn-modal-save").click(function () {
        $('#selectPlayersModal').modal('hide');
        saveSubstitutePlayers(CLUBTOBEEDITED.HOMECLUB);
    });

    $(".chosen-select").unbind('change');
    $(".chosen-select").on('change', function(evt, params) {

        var editedPlayerId = "";

        if ( params.hasOwnProperty('selected') ){
            editedPlayerId = params['selected'];
            clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.SUBSTITUTE;

        } else if (params.hasOwnProperty('deselected') ) {
            editedPlayerId = params['deselected'];
            clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.RELATED;
        }
    });

    $('#selectPlayersModal').modal('show');
}

function selectVisitorClubStartingPlayers() {

    console.log("selectVisitorClubStartingPlayers");
    $(".modal-title").html("Selecione os 11 jogadores titulares do " + visitorClubName);

    populateSelect(CLUBTOBEEDITED.VISITORCLUB, true);

    $("#btn-modal-save").unbind("click");
    $("#btn-modal-save").click(function () {
        $('#selectPlayersModal').modal('hide');
        saveStartingPlayers(CLUBTOBEEDITED.VISITORCLUB);
    });

    $(".chosen-select").unbind('change');
    $(".chosen-select").on('change', function(evt, params) {

        var editedPlayerId = "";

        if ( params.hasOwnProperty('selected') ){
            editedPlayerId = params['selected'];
            clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.STARTING;

        } else if (params.hasOwnProperty('deselected') ) {
            editedPlayerId = params['deselected'];
            clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.RELATED;
        }
    });

    $('#selectPlayersModal').modal('show');

}

function selectVisitorClubSubstitutePlayers() {
    console.log("selectVisitorClubSubstitutePlayers");
    $(".modal-title").html("Selecione os jogadores reservas do " + visitorClubName);

    populateSelect(CLUBTOBEEDITED.VISITORCLUB, false);

    $("#btn-modal-save").unbind("click");
    $("#btn-modal-save").click(function () {
        $('#selectPlayersModal').modal('hide');
        saveSubstitutePlayers(CLUBTOBEEDITED.VISITORCLUB);
    });

    $(".chosen-select").unbind('change');
    $(".chosen-select").on('change', function(evt, params) {

        var editedPlayerId = "";

        if ( params.hasOwnProperty('selected') ){
            editedPlayerId = params['selected'];
            clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.SUBSTITUTE;

        } else if (params.hasOwnProperty('deselected') ) {
            editedPlayerId = params['deselected'];
            clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.RELATED;
        }
    });

    $('#selectPlayersModal').modal('show');
}

function saveStartingPlayers(CLUBTOBEEDITED) {

    $('#selectPlayersModal').modal('hide');

    console.log("Fechou starting de " + CLUBTOBEEDITED);
    console.log($(".chosen-select").chosen().val());
}

function saveSubstitutePlayers(CLUBTOBEEDITED) {

    $('#selectPlayersModal').modal('hide');

    console.log("Fechou substitute de " + CLUBTOBEEDITED);
    console.log($(".chosen-select").chosen().val());
}