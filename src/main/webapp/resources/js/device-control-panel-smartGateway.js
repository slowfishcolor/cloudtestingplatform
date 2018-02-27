/**
 * Created by Prophet on 2018/2/26.
 */
$(function () {
    addBtnClickEvent();
});

// STOMP connect
var url = brokerUrl;
var login = brokerUser;
var passcode = brokerPassword;
var sendDestination = "/topic/messenger.topic.server.";
var subDestination = "/topic/messenger.topic.device.";
// STOMP client
var client;

function connectToBroker() {
    client = Stomp.client(url);
    // on connect callback
    var onconnect = function (frame) {
        // add deviceId to subDestination
        subDestination += physicalDeviceId;
        sendDestination += deviceId;
        // subscribe receive topic
        client.subscribe(subDestination, function (message) {
            // add new data
            // console.log(message.body);
            var payloadStr = message.body;
            var payload = eval('(' + payloadStr + ')');;
            var data = payload.data;
            // console.log(data);
            addData(data);
        })
        // default display temp data
        // client.send(sendDestination, {foo: 1}, "hello");
        $("#btn-connect").attr("disabled", true);
        $("#btn-disconnect").attr("disabled", false);
        $("#btn-send").attr("disabled", false);
        $("#dropdownPort").attr("disabled", true);
        $("#dropdownBound").attr("disabled", true);
    }
    // connect to broker
    client.connect(login, passcode, onconnect);
}

function disconnect() {
    client.disconnect(function () {
        // disconnect call back
        $("#btn-connect").attr("disabled", false);
        $("#btn-disconnect").attr("disabled", true);
        $("#btn-send").attr("disabled", true);
        $("#dropdownPort").attr("disabled", false);
        $("#dropdownBound").attr("disabled", false);
    });
}

function addData(data) {
    var instructions = data.instructions;
    for (var index in instructions) {
        if ($("#panel-instruction").is(":visible")) {
            addReceiveText(instructions[index].valueString);
        }
    }
}

function addBtnClickEvent() {
    $("#btn-connect").click(function () {
        connectToBroker();
    });
    $("#btn-send").click(function () {
        sendSingleInstruction($("#input-instruction").val());
    });
    $("#input-instruction").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            if ($("#btn-send").prop("disabled") == false) {
                $("#btn-send").click();
            }
        }
    });
    $("#btn-disconnect").click(disconnect);
}

function sendSingleInstruction(instructionString) {
    $.post("../api/mq/smartGateway/sendSingleInstruction", {
        deviceId: deviceId,
        instructionString: instructionString,
        port: "/dev/ttyUSB0"
    }, function (data) {
        console.log(data.code);
        addSendText(instructionString);
    })
}

function addSendText(text) {
    var d = document.createElement("div");
    d.setAttribute("class", "list-group-item");
    var t = document.createTextNode(currentTimeStr()+ ": " + text);
    d.appendChild(t);
    $(d).hide().prependTo($('#send-list')).slideDown();
}

function addReceiveText(text) {
    var d = document.createElement("div");
    d.setAttribute("class", "list-group-item");
    var t = document.createTextNode(currentTimeStr()+ ": " + text);
    d.appendChild(t);
    $(d).hide().prependTo($('#receive-list')).slideDown();
}