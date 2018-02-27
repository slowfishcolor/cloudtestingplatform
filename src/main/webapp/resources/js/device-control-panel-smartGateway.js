/**
 * Created by Prophet on 2018/2/26.
 */
$(function () {
    initICheck();
    initFileUpload();
    addBtnClickEvent();
    $("#loading").hide();
});

// STOMP connect
var url = brokerUrl;
var login = brokerUser;
var passcode = brokerPassword;
var sendDestination = "/topic/messenger.topic.server.";
var subDestination = "/topic/messenger.topic.device.";
// STOMP client
var client;

var isConnect = false;

var scriptName = "UUTSenseHatFull.json";

function connectToBroker() {
    client = Stomp.client(url);
    // on connect callback
    var onconnect = function (frame) {
        isConnect = true;
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
        isConnect = false;
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
    if ($("#panel-instruction").is(":visible")) {
        for (var index in instructions) {
            addReceiveText(instructions[index].valueString);
        }
    } else if ($("#panel-script").is(":visible")) {
        $("#dataList").find("tr:gt(0)").remove();

        for (var index in instructions) {
            var instruction = instructions[index];
            var tr = $("<tr><td><input type='checkbox' ></td><td></td><td ></td><td ></td><td></td><td ></td><td ></td><td ></td><td ></td></tr>");
            $("#dataList").append(tr);
            $(tr).find("td:eq(1)").text(instruction.name);
            $(tr).find("td:eq(2)").text(instruction.valueString);
            $(tr).find("td:eq(3)").text(instruction.valueNumber);
            $(tr).find("td:eq(4)").text(instruction.minValue);
            $(tr).find("td:eq(5)").text(instruction.maxValue);
            $(tr).find("td:eq(6)").text(timeStampToDateStrMs(instruction.timestamp));
            $(tr).find("td:eq(7)").text(instruction.port);
            $(tr).find("td:eq(8)").text(instruction.remark);

        }

        $(".datashow").show();
        $("#loading").hide();
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
    $("#btn-perform-task").click(performTask);
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

function performTask() {
    $(".datashow").hide();
    $("#loading").show();
    if (!isConnect) {
        connectToBroker();
    }
    $.get("../api/file/" + scriptName, function (data) {
        // var obj = JSON.parse(data);
        // console.log(obj);
        $.post("../api/performTask", {
            "deviceId": deviceId,
            "script": scriptName,
            "instructionsJson": data
        }, function () {
            console.log("success");
        })
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


function initICheck() {
    $('.icheck-list input').on('ifClicked', function(event){
        if (this.id === "input-script-default") {

        } else {

        }
    }).iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });
}

function initFileUpload() {
    $("#input-b1").fileinput({
        uploadAsync: true,
        uploadUrl: "./api/uploadFile",
        maxFileSize: 160000,
        allowedFileExtensions: ['json'],
        uploadExtraData: function () {
            return {'file-name': scriptName};
        },
        maxFileCount: 1,
        previewSettings: {
            text: {width: "100%", height: "100px"}
        }
    }).on('filepreupload', function(event, data, previewId, index) {     //上传中
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader, filenames = data.filenames;
        console.log('文件'+ filenames[0] +'正在上传');

        newDevice.fileName = filenames[0];

    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        console.log('文件上传成功！'+data.id);
    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！'+data.id);
    });
}
