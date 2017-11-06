/**
 * Created by Prophet on 2017/11/5.
 */
$(function () {
    connectToBroker();
    initEcharts();
});

// STOMP connect
var url = "ws://www.prophet-xu.com:61614/stomp";
var login = "";
var passcode = "";
var sendDestination = "/topic/messanger.topic.server";
var subDestination = "/topic/messanger.topic.device.";
// STOMP client
var client;

function connectToBroker() {
    client = Stomp.client(url);
    // on connect callback
    var onconnect = function (frame) {
        // add deviceId to subDestination
        subDestination += deviceId;
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
        client.send(sendDestination, {foo: 1}, "start temp");
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

    // console.log(chartData);
    chart.setOption({
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