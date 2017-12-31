/**
 * Created by Prophet on 2017/11/5.
 */
$(function () {

    config =  eval('(' + configStr + ')');
    setUpComboBox(config);
    connectToBroker();
    initEcharts();
    addBtnClickEvent();
});

// STOMP connect
var url = "ws://www.prophet-xu.com:61614/stomp";
var login = "";
var passcode = "";
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
        client.send(sendDestination, {foo: 1}, "hello");
    }
    // connect to broker
    client.connect(login, passcode, onconnect);
}

var chart;
var option;
var chartData = [];


function initEcharts() {
    chart = echarts.init(document.getElementById('chart'));
    option = {
        title: {
            text: '波形图'
        },
        legend: {
            data:['Voltage']
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                return 'Voltage: ' + params.value[1] + ' V';
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'value',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            name: 'Voltage',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: true
            }
        },
        series: [{
            name: 'Voltage',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: chartData
        }]
    };

    chart.setOption(option);
}

var count = 0;

function addData(data) {
    count = 0;
    var sampleCount = data.sampleCount;
    var values = data.value;
    while (chartData.length > 0) {
        chartData.shift();
    }
    for (index in values) {
        chartData.push(newData(values[index]))
    }

    var minMax = getMinMaxAvg(values);

    // console.log(chartData);
    chart.setOption({
        yAxis:{
            min: minMax[2],
            max: minMax[3]
        },
        series: [{
            data: chartData
        }]
    });
}

function newData(dataValue) {
    count++;
    return {
        // name: count,
        value: [
            count,
            dataValue
        ]
    }
}


function startAcquire() {
    // client.send(sendDestination, {foo: 1}, "start");
    sendStartStop(deviceId, true);
}

function stopAcquire() {
    // client.send(sendDestination, {foo: 1}, "stop");
    sendStartStop(deviceId, false);
}

function addBtnClickEvent() {

    $("#startBtn").click(startAcquire);
    $("#stopBtn").click(stopAcquire);
    $("#setupBtn").click(setup);

    $(".dropdown-menu a").click(function () {
        var item = $(this).text();
        var dropdown = $(this).parents(".dropdown").get(0);
        $(dropdown).find(".chosenItem").text(item);
    });
}

function sendStartStop(deviceId, isStart) {
    $.ajax({
        type: "POST",
        url: "../api/mq/pcie6320/sendStartStopCommand",
        data: {deviceId : deviceId, isStart : isStart},
        success: function (data) {
            if (data.code == 200) {
                if (isStart) {
                    $("#startBtn").addClass("disabled");
                    $("#stopBtn").removeClass("disabled");
                } else {
                    $("#stopBtn").addClass("disabled");
                    $("#startBtn").removeClass("disabled");
                }
            }
        },
        dataType: "json"
    });
}

function sendSetup(deviceId) {
    $.ajax({
        type: "POST",
        url: "../api/mq/pcie6320/sendSetupCommand",
        data: {
            deviceId: deviceId,
            channel: trim($("#dropdownMenu1").text()),
            method: trim($("#dropdownMenu2").text()),
            minVoltage: $("#minVoltage").val(),
            maxVoltage: $("#maxVoltage").val(),
            samples: $("#samples").val(),
            rate: $("#rate").val(),
            command: "setup"
        },
        dataType: "json"
    });
}

function setup() {
    sendSetup(deviceId);
}

function trim(s){
    return s.replace(/(^\s*)|(\s*$)/g, "");
}

function setUpComboBox(config) {
    var channel = config.config.channel;
    var method = config.config.method;
    var first = true;
    for (var key in channel) {
        if (channel[key]) {
            $("#dropdownMenu1-content").append('<li role="presentation"><a role="menuitem" tabindex="-1" >' + key + '</a></li>');
            if (first) {
                first = false;
                $("#dropdownMenu1-chosenItem").text(key);
            }
        }
    }
    first = true;
    for (var key in method) {
        if (method[key]) {
            $("#dropdownMenu2-content").append('<li role="presentation"><a role="menuitem" tabindex="-1" >' + key + '</a></li>');
            if (first) {
                first = false;
                $("#dropdownMenu2-chosenItem").text(key);
            }
        }
    }
}