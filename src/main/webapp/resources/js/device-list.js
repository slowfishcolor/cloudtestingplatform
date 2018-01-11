/**
 * Created by Prophet on 2017/11/15.
 */
$(function () {
    $("#deleteDeviceBtn").click(deleteDevice);
    $("#addDeviceBtn").click(function () {
        location.href="device-add";
    });
});
function deleteDevice() {
    var deviceIdStrs = "";
    $("#deviceList").find("input:checked").each(function () {
        var deviceId = $(this).attr("deviceId");
        if (deviceId) {
            deviceIdStrs += deviceId + ",";
        }
    });
    if (deviceIdStrs === "") {
        Alert("没有选中设备！");
        return;
    }
    console.log(deviceIdStrs);

    $.ajax( {
        type : "POST",
        url : "api/deleteDevice",
        data : "deviceIdStrs=" + deviceIdStrs,
        success : function(data) {
            if (data.code == 200) {
                location.reload();
            } else {
                alert(data.message);
            }
        },
        dataType : "json"
    });
}