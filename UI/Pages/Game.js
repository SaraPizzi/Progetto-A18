var server='http://localhost:8080';
server += '/api/v1';

function load_game(gameID){
       
    /*Define endpoint to load game*/
    var xhr = RESTrequest('POST', "//", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        }else{
            
        }
    };  
}

var gameboard = [];
var gameID = 0;
var rows=6;
var columns=7;

function newGame(rows, columns){

    for(var i=0; i<rows; i++){
        gameboard[i]=[];
        for(var j=0; j<columns; j++){
            gameboard[i][j]=0;
        }
    }
    console.log(gameboard);

}

function addMove(player, move){
    
    for(var i=rows-1; i>=0; i--){
        if(gameboard[i][move]===0){
            gameboard[i][move]=player;
            var data=JSON.stringify({"player":player,"move":move});
            //RESTrequest("POST", "/game/"+gameID+"/move",data);
            console.log(gameboard);
            PlayerMove.cont++;
            PlayerMove.y_coordinate = i;
            PlayerMove.isallowed = "true";
            return;
        }

    }

    PlayerMove.isallowed = "false";
  //  alert("The move is not allowed. The column is already full");
}

function RESTrequest(type, endpoint, data){

    var xhr = new XMLHttpRequest();
    xhr.open(type, server + endpoint, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.location.href="../Index.html";
            var json = JSON.parse(xhr.responseText);
            console.log(json.email + ", " + json.password);
        }
    };
    xhr.send(data);
    return xhr;
}

