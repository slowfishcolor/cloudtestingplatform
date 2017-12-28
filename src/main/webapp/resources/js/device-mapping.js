$(function () {

    chart.setOption(option);

});

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


initChart();