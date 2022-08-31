const inputField = document.getElementById("chat-input");
const outputArea = document.getElementById("chat-area");

var socket;
const socketRoute = document.getElementById("ws-route").value;
socket = new WebSocket(socketRoute.replace("http", "ws"));

inputField.onkeydown = (event) => {
    if (event.key === 'Enter') {
        socket.send(inputField.value);
        inputField.value = '';
    }
}

socket.onopen = () => socket.send("User Connected");
socket.onmessage = (event) => {
    if (event.data === "pong") {
        // console.log(event.data);
    } else {
        outputArea.value += '\n' + event.data;
    }
}

function heartbeat() {
    socket.send("ping")
    // console.log("ping sent");
}
setInterval(heartbeat, 5000);