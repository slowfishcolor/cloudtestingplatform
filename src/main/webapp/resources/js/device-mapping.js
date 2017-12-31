$(function () {

    initChart();

    chart.setOption(option);

    config =  eval('(' + configStr + ')');
    initJsonEditor(config);

    addBtnClickEvent();


    $('#alert').on('hidden.bs.modal', function () {
        location.reload();
    })

});

var config;

var editor;

var data = {
    "name":"physicalDeviceId",
    "itemStyle": {
      "normal": {
          "color":"#111"
      }
    },
    "children":[
        {"name":"deviceId_1"},
        {"name":"deviceId_2"}
    ]
};

var count = 0;

var chart;

var option = {
    tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
    },
    series: [
        {
            type: 'tree',

            data: [data],

            top: '1%',
            left: '25%',
            bottom: '1%',
            right: '25%',

            symbolSize: 16,

            label: {
                normal: {
                    position: 'left',
                    verticalAlign: 'middle',
                    align: 'right',
                    fontSize: 12
                }
            },

            leaves: {
                label: {
                    normal: {
                        position: 'right',
                        verticalAlign: 'middle',
                        align: 'left'
                    }
                }
            },


            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750
        }
    ]
}

function initChart() {
    chart = echarts.init(document.getElementById('chart'));
    chart.hideLoading();

    chart.on('click', function (params) {
        console.log(count++);
        console.log(params);
    });

}

function initJsonEditor(jsonObj) {
    var container = document.getElementById("jsoneditor");
    var options;
    if (virtual == 1) {
        options = {
            mode: 'tree',
            onEditable: function (node) {
                return {
                    field: false,
                    value: true
                }
            }
        };
    } else {
        options = {
            mode: 'view'
        }
    }

    editor = new JSONEditor(container, options);

    editor.set(jsonObj.config);
    editor.expandAll();
}

function addBtnClickEvent() {
    $("#saveConfigBtn").click(saveConfig);
    $("#resetConfigBtn").click(resetConfig);
}

function saveConfig() {
    var configJson = editor.get();
    config.config = configJson;
    var newConfigStr = JSON.stringify(config);
    $.post(
            "../api/updateDeviceConfig",
        {
            "deviceId": deviceId,
            "config": newConfigStr
        },
        function (data) {
            if (data.code == 200) {
                Alert("保存成功！");
            } else {
                Alert("保存失败！");
            }
        }
    );
}

function resetConfig() {
    editor.set(config.config);
}

