var socket = new WebSocket("ws://" + location.hostname + ":" + location.port);

socket.onopen = function (e) {
    console.log("Connection status --> " + this.readyState);
}
socket.onclose = function (e) {
    console.log("Disconnection status --> " + this.readyState);
    alert("You are DISCONNECTED");
}

$(document).ready(function(){

    $('#author').click(function(){

    });

});