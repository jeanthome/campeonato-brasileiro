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

var LINE_UP_DIV_ID = {
    HOMECLUB_STARTING: "#home-club-starting-line-up",
    HOMECLUB_SUBSTITUTE: "#home-club-substitute-line-up",
    VISITORCLUB_STARTING: "#visitor-club-starting-line-up",
    VISITORCLUB_SUBSTITUTE: "#visitor-club-substitute-line-up"
};

var HALF_ENUM = {
    FIRST: "FIRST_HALF",
    SECOND: "SECOND_HALF"
};


$(document).ready(function () {

    $('#stating-players-home-selector').click(function () {
        pickPlayersInSelect(CLUBTOBEEDITED.HOMECLUB, true);
    })


    $("#substitute-players-home-selector").click(function () {
        pickPlayersInSelect(CLUBTOBEEDITED.HOMECLUB, false);
    });

    $("#stating-players-visitor-selector").click(function () {
        pickPlayersInSelect(CLUBTOBEEDITED.VISITORCLUB, true);
    });

    $("#substitute-players-visitor-selector").click(function () {
        pickPlayersInSelect(CLUBTOBEEDITED.VISITORCLUB, false);
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

    setRound();

});

function setRound() {

    var roundNumber = matchLoaded.roundNumber;
    $("#round-number").html("Campeonato Brasileiro - Rodada " + roundNumber);
}

function populateSelectToLineUp(clubToBeEdited, isSelectingStartingPlayers) {

    var options = "";

    $('#modal-player-select').html('');

    for (playerId in clubs[clubToBeEdited].allPlayers) {

        if (clubs[clubToBeEdited].allPlayers.hasOwnProperty(playerId)) {

            var tempPlayer = clubs[clubToBeEdited].allPlayers[playerId];
            var playerName = tempPlayer.displayName;
            var playerNumber = tempPlayer.number;

            /*Verifica se o jogador já foi selecionado*/
            if ((isSelectingStartingPlayers && tempPlayer["status"] == PLAYERSTATUS.STARTING) ||
                !isSelectingStartingPlayers && tempPlayer["status"] == PLAYERSTATUS.SUBSTITUTE) {

                options += '<option value="' + playerId + '" selected>' + playerNumber + " - " + playerName + '</option>';

            } else if (tempPlayer["status"] == PLAYERSTATUS.RELATED) {
                options += '<option value="' + playerId + '" >' + playerNumber + " - " + playerName + '</option>';
            } else {
                options += '<option value="' + playerId + '" disabled>' + playerNumber + " - " + playerName + '</option>';
            }
        }
    }

    $("#modal-player-select").html(options);
    $("#modal-player-select").trigger('chosen:updated');
}

function loadClubsBadges() {

    console.log(matchId + ": " + homeClubId + " " + visitorClubId);
    $("#home-badge").html('<img src="http://localhost:8080/clubs/' + homeClubId + '/badge"/>');
    $("#visitor-badge").html('<img src="http://localhost:8080/clubs/' + visitorClubId + '/badge"/>');
}

function getPlayersFromClubs() {

    var urlHomeClub = "/clubs/" + homeClubId + "/players";
    $.get(urlHomeClub, function (data) {
        homeClubAllPlayers = buildPlayersLookup(data);
        clubs[CLUBTOBEEDITED.HOMECLUB].allPlayers = homeClubAllPlayers;

        updatePlayersStatusFromPersistedPlayers(CLUBTOBEEDITED.HOMECLUB, PLAYERSTATUS.STARTING,
            matchLoaded.homeClubStartingPlayers);
        updatePlayersStatusFromPersistedPlayers(CLUBTOBEEDITED.HOMECLUB, PLAYERSTATUS.SUBSTITUTE,
            matchLoaded.homeClubSubstitutePlayers);

        updateLineUp(CLUBTOBEEDITED.HOMECLUB, PLAYERSTATUS.STARTING);
        updateLineUp(CLUBTOBEEDITED.HOMECLUB, PLAYERSTATUS.SUBSTITUTE);

    });

    var urlVisitorClub = "/clubs/" + visitorClubId + "/players";
    $.get(urlVisitorClub, function (data) {
        visitorClubAllPlayers = buildPlayersLookup(data);
        clubs[CLUBTOBEEDITED.VISITORCLUB].allPlayers = visitorClubAllPlayers;

        updatePlayersStatusFromPersistedPlayers(CLUBTOBEEDITED.VISITORCLUB, PLAYERSTATUS.STARTING,
            matchLoaded.visitorClubStartingPlayers);
        updatePlayersStatusFromPersistedPlayers(CLUBTOBEEDITED.VISITORCLUB, PLAYERSTATUS.SUBSTITUTE,
            matchLoaded.visitorClubSubstitutePlayers);

        updateLineUp(CLUBTOBEEDITED.VISITORCLUB, PLAYERSTATUS.STARTING);
        updateLineUp(CLUBTOBEEDITED.VISITORCLUB, PLAYERSTATUS.SUBSTITUTE);
    });
}

/**
 * Verifica quais jogadores da lista geral estão como titulares ou reservas, usando como referência
 * a lista persistida no banco.
 *
 * @param clubToBeEdited Clube pro qual a verificação será feita.
 * @param newStatus O novo status a ser atribuido caso o jogador estaja na lista.
 * @param playerList Lista de jogadores para comparação.
 */
function updatePlayersStatusFromPersistedPlayers(clubToBeEdited, newStatus, playerList) {

    for (playerId in playerList) {
        if (playerList.hasOwnProperty(playerId)) {
            var player = playerList[playerId];

            if (clubs[clubToBeEdited].allPlayers.hasOwnProperty(player.id)) {
                clubs[clubToBeEdited].allPlayers[player.id].status = newStatus;
            }
        }
    }
}

function buildPlayersLookup(data) {

    var lookup = {};
    for (var i = 0; i < data.length; i++) {
        var playerId = data[i].identificator;
        var player = data[i];
        player["status"] = PLAYERSTATUS.RELATED;
        lookup[playerId] = player;
    }
    return lookup;
}

function pickPlayersInSelect(club, isStartingPlayers) {

    var clubName = (club === CLUBTOBEEDITED.HOMECLUB ? homeClubName : visitorClubName);
    var newPlayerStatus;

    if (isStartingPlayers) {
        newPlayerStatus = PLAYERSTATUS.STARTING;
        $(".modal-title").html("Selecione os 11 jogadores titulares do " + clubName);
    } else {
        newPlayerStatus = PLAYERSTATUS.SUBSTITUTE;
        $(".modal-title").html("Selecione os jogadores reservas do " + clubName);
    }

    populateSelectToLineUp(club, isStartingPlayers);


    $("#btn-modal-save").unbind("click");
    $("#btn-modal-save").click(function () {
        persistePlayers(club, isStartingPlayers);
        updateLineUp(club, newPlayerStatus);
    });


    $("#modal-player-select").unbind('change');
    $("#modal-player-select").on('change', function (evt, params) {

        var editedPlayerId = "";
        if (params.hasOwnProperty('selected')) {
            editedPlayerId = params['selected'];
            clubs[club].allPlayers[editedPlayerId]["status"] = newPlayerStatus;

        } else if (params.hasOwnProperty('deselected')) {
            editedPlayerId = params['deselected'];
            clubs[club].allPlayers[editedPlayerId]["status"] = PLAYERSTATUS.RELATED;
        }
    });

    $('#selectPlayersModal').on('show.bs.modal', function () {

        /*Define o limite de jogadores selecionáveis, caso seja para vaga de titular.*/
        if (isStartingPlayers) {
            $(".chosen-select").chosen({
                max_selected_options: 11,
                width: "100%"
            });
        } else {
            $(".chosen-select").chosen({
                width: "100%"
            });
        }
    });

    $('#selectPlayersModal').modal('show');
}

function persistePlayers(clubToBeEdited, isStartingPlayers) {

    $('#selectPlayersModal').modal('hide');


    var idsList = getIdListToPersistePlayers(clubToBeEdited, isStartingPlayers);

    $.ajax({
        type: 'POST',
        url: '/admin/match/persistePlayers',
        data: {
            matchId: matchId,
            clubType: clubToBeEdited,
            playersIdList: idsList,
            isStartingPlayers: isStartingPlayers
        },
        success: function (msg) {
            //alert(msg);
        }
    });

    console.log($(".chosen-select").chosen().val());
}

function getIdListToPersistePlayers(club, isStartingPlayer) {

    var playerStatus = isStartingPlayer ? PLAYERSTATUS.STARTING : PLAYERSTATUS.SUBSTITUTE;
    var idList = "";

    for (playerId in clubs[club].allPlayers) {
        if (clubs[club].allPlayers.hasOwnProperty(playerId)) {
            var tempPlayer = clubs[club].allPlayers[playerId];

            if (tempPlayer.status === playerStatus) {
                idList += playerId + ";";
            }
        }
    }
    return idList;
}

function updateLineUp(club, playerStatus) {

    var divId;

    if (CLUBTOBEEDITED.HOMECLUB === club) {

        if (PLAYERSTATUS.STARTING === playerStatus) {
            divId = LINE_UP_DIV_ID.HOMECLUB_STARTING;
        } else {
            divId = LINE_UP_DIV_ID.HOMECLUB_SUBSTITUTE;
        }
    } else {

        if (PLAYERSTATUS.STARTING === playerStatus) {
            divId = LINE_UP_DIV_ID.VISITORCLUB_STARTING;
        } else {
            divId = LINE_UP_DIV_ID.VISITORCLUB_SUBSTITUTE;
        }
    }

    $(divId).html("");

    for (playerId in clubs[club].allPlayers) {

        var listItem = '<li class="list-group-item clearfix"><div class="row">';

        if (clubs[club].allPlayers.hasOwnProperty(playerId)) {

            var tempPlayer = clubs[club].allPlayers[playerId];
            var playerName = tempPlayer.displayName
            var playerNumber = tempPlayer.number;
            var playerPosition = tempPlayer.position.abbreviation;

            if (tempPlayer.status === playerStatus) {

                if (club === CLUBTOBEEDITED.HOMECLUB) {
                    listItem += getLineUpDivWithNameAndPosition(playerName, playerPosition);
                    listItem += getLineUpDivWithNumber(playerNumber);
                } else {
                    listItem += getLineUpDivWithNumber(playerNumber);
                    listItem += getLineUpDivWithNameAndPosition(playerName, playerPosition);
                }

                listItem += '</div></li>';

                $(divId).append(listItem);
            }
        }
    }
}


function getLineUpDivWithNameAndPosition(playerName, playerPosition) {

    var div = '<div class="col-md-10 line-up-item"><div class="col-md-12 player-name-wrapper">' +
        '<span class="player-name">' + playerName + '</span></div>' +
        '<div class="col-md-12 player-position-wrapper">' +
        '<span class="player-position">' + playerPosition.toUpperCase() + '</span></div></div>';
    return div;
}

function getLineUpDivWithNumber(playerNumber) {
    var div = '<div class="col-md-2 player-number-wrapper">';

    if (playerNumber < 10) {
        div += '<strong class="player-number">&nbsp' + playerNumber + '</strong></div>';
    } else {
        div += '<strong class="player-number">' + playerNumber + '</strong></div>';
    }
    return div;
}


function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}
