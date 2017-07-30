
$(document).ready(function () {

    setScore();

    $("#modal-goal-owner-player-select").chosen({
        max_selected_options: 1,
        width: "100%"
    });

    $("#insert-goal-home-selector").click(function () {
        insertGoal(CLUBTOBEEDITED.HOMECLUB);
    });

    $("#insert-goal-visitor-selector").click(function () {
        insertGoal(CLUBTOBEEDITED.VISITORCLUB);
    });

});

function setScore() {

    $("#score-home-goals").html(matchLoaded.homeClubGoals.length);
    $("#score-visitor-goals").html(matchLoaded.visitorClubGoals.length);
}

function insertGoal(club) {

    populateSelectToGoal(club);

    $("#btn-modal-goal-save").unbind("click");
    $("#btn-modal-goal-save").click(function () {
        persisteGoal(club);

    });
}
function populateSelectToGoal(clubToBeEdited) {

    var options = "";
    $("#modal-goal-owner-player-select").html('');

    for (playerId in clubs[clubToBeEdited].allPlayers) {

        if (clubs[clubToBeEdited].allPlayers.hasOwnProperty(playerId)) {

            var tempPlayer = clubs[clubToBeEdited].allPlayers[playerId];

            if (tempPlayer["status"] === PLAYERSTATUS.STARTING) {
                var playerName = tempPlayer.displayName;
                var playerNumber = tempPlayer.number;
                options += '<option value="' + playerId + '">' + playerNumber + " - " + playerName + '</option>';
            }
        }
    }

    $("#modal-goal-owner-player-select").html(options).trigger('chosen:updated');
}

function persisteGoal(club) {

    $("#modal-goal").modal('hide');

    var goalOwnerId = $("#modal-goal-owner-player-select").chosen().val();
    var half = $('input[name="goal-half"]:checked').val();
    var minute = $("#goal-minute").val();
    var ownGoal = $("#own-goal").is(':checked') ? true : false;
    var title = $("#goal-title").val();
    var description = $("#goal-description").val();

    var goalObject = {
        "match": matchId,
        "clubType": club,
        "goalOwner": goalOwnerId[0],
        "half": half,
        "minute": minute,
        "ownGoal": ownGoal,
        "title": title,
        "description": description
    };

    $.ajax({
        type: 'PUT',
        url: '/admin/match/goal',
        contentType: 'application/json',
        data: JSON.stringify(goalObject),
        success: function (result) {
            buildGoal(goalObject);
        },
        error: function (e) {
            alert(e);
        }
    });

}

/**
 * Adiciona o novo gol na lista dos gols já existentes e atualiza o placar.
 *
 * @param goalObject Objeto com as informações do gol marcado.
 */
function buildGoal(goalObject) {

    var player = clubs[goalObject.clubType].allPlayers[goalObject.goalOwner];

    var goal = {
        "title": goalObject.title,
        "description": goalObject.description,
        "half": goalObject.half == 0 ? HALF_ENUM.FIRST : HALF_ENUM.SECOND,
        "minute": goalObject.minute,
        "ownGoal": goalObject.ownGoal,
        "owner": {
            "displayName": player.displayName,
            "id": player.identificator,
            "number": player.number,
            "positionAbbreviation": player.position.abbreviation
        }
    };

    if (goalObject.clubType === CLUBTOBEEDITED.HOMECLUB) {
        if (goalObject.ownGoal) {
            matchLoaded.visitorClubGoals.push(goal);
        } else {
            matchLoaded.homeClubGoals.push(goal);
        }
    } else {
        if (goalObject.ownGoal) {
            matchLoaded.homeClubGoals.push(goal);
        } else {
            matchLoaded.visitorClubGoals.push(goal);
        }
    }

    setScore();
}
