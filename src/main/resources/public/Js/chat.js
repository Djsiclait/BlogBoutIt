var socket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/:username/chatboard");

socket.onopen = function (e) {
    console.log("Connection status --> " + this.readyState);
}
socket.onclose = function (e) {
    console.log("Disconnection status --> " + this.readyState);
    alert("You are DISCONNECTED");
}

$(document).ready(function(){

    var critic = {
        name: '',
        message: ''
    };

    $('#author').click(function(){
        critic['name'] = $('#critic').val();
        /*Add chat popup logic*/
    });

    $('#send').click(function(){

    });

});