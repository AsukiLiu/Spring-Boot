$(function(){
    var ws = new WebSocket("ws://localhost:8082/echo");
    ws.onopen = function(){
    };
    ws.onclose = function(){
    };
    ws.onmessage = function(message){
        $("#result").append(message.data).append("<br>");
    };
    ws.onerror = function(event){
        alert("Connection failed");
    };

    $("#form").submit(function(){
        ws.send($("#message").val());
        $("#message").val("")
        return false;
    });
});