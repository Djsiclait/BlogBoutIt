$(document).ready(function () {

    $('#critique').click(function() {
        var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");

        webSocket.onopen = function () {
            alert("You are now chatting!");
        }

        webSocket.onmessage = function (message) {
            updateChat(message);
        };

        webSocket.onclose = function () {
            alert("WebsSocket connection closed")
        };

        id("send").addEventListener("click", function () {
            sendMessage(id("message").value);
        });

        id("message").addEventListener("keypress", function (e) {
            if (e.keyCode == 13) {
                sendMessage(e.target.value);
            }
        });

        function sendMessage(message) {
            if (message != "") {
                webSocket.send(message);
                id("message").value = "";
            }
        }

        function updateChat(message) {
            var data = JSON.parse(message.data);
            insert("chat", data.userMessage);
            id("userlist").innerHTML = "";
            data.userlist.forEach(function (user) {
                insert("userlist", "<li>" + user + "</li>");
            });
        }

        function insert(targetID, message) {
            id(targetID).insertAdjacentHTML("afterbegin", message);
        }

        function id(id) {
            return document.getElementById(id);
        }
    });
});