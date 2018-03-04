/**
 * Created by Prophet on 2018/3/2.
 */
$(function () {
    initEcharts();
    listStatisticCount();
    getNewDevice();
    getNewTask();
    $(window).on('resize',function(){ chart.resize(); });
});

var chart;
var option;
var chartData = [];

function initEcharts() {
    chart = echarts.init(document.getElementById('chart'));
    option = {


        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        series : [
            {
                name: '测试设备',
                type: 'pie',
                radius : '80%',
                center: ['50%', '50%'],
                data:[
                    {value:0, name:'物理设备'},
                    {value:0, name:'虚拟设备'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ],
        color:['#418bca', '#5bb85d']
    };

    chart.setOption(option);
}

function listStatisticCount() {
    $.get("api/listStatisticCount", function (data) {
        $("#deviceCount").text(data.deviceCount);
        $("#physicalDeviceCount").text(data.physicalDeviceCount);
        $("#virtualDeviceCount").text(data.virtualDeviceCount);
        $("#modelCount").text(data.modelCount);
        $("#dataCount").text(data.dataCount);
        $("#taskCount").text(data.taskCount);
        chart.setOption({series:
            [{data:
                [{
                    value: data.physicalDeviceCount, name: '物理设备'
                },{
                    value: data.virtualDeviceCount, name: '虚拟设备 '
                }]}]});
    });
}

function getNewDevice() {
    $.get("api/getNewDevice", function (data) {
        $("#registerTimeStr").text(data.registerTimeStr);
        $("#deviceId").text(data.deviceId);
        $("#deviceId").attr("href", "device-info/" + data.deviceId);
        $("#virtualStr").text(data.virtualStr);
        $("#modelName").text(data.model.name);
        $("#modelName").attr("href", "model-info/" + data.model.id);
        $("#image").attr("src", "api/image/" + data.model.imageId);

    });
}

function getNewTask() {
    $.get("api/getNewTask", function (data) {
        $("#taskTime").text(data.timeStr);
        $("#script").text(data.script);
        $("#taskId").text(data.taskName);
        $("#taskDeviceId").text(data.deviceId);
    });
}