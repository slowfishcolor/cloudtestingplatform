/**
 * Created by Prophet on 2017/11/15.
 */

function Alert(msg) {
    $("#alert-content").text(msg);
    $("#alert").modal();
}

$(function () {
    $(document).on('change', 'table thead [type="checkbox"]', function(e) {
        e && e.preventDefault();
        var $table = $(e.target).closest('table'), $checked = $(e.target).is(':checked');
        $('tbody [type="checkbox"]', $table).prop('checked', $checked);
    });
});

Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}

function timeStampToDateStrMs(timestamp) {
    return new Date(timestamp).format("yyyy-MM-dd hh:mm:ss.S");
}

function getMinMaxAvg(values) {

    var min = values[0];
    var max = values[0];
    var avg = 0;

    for (var i in values) {
        min = min > values[i] ? values[i] : min;
        max = max < values[i] ? values[i] : max;
        avg += values[i];
    }

    var tenPercent = (max - min) / 10;
    avg /= values.length;

    return [min, max, min - tenPercent, max + tenPercent, avg];
}