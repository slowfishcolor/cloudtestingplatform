/**
 * Created by Prophet on 2017/12/31.
 */
$(function () {
    initICheck();
    initStepBarSwitch();
    $(".vir").hide();
    $(".phy").show();

    getModelList();
    getDeviceList();

    initFileUpload();

    addBtnEvent();

    $("#btn-finish").hide();
});

var newDevice = {
    deviceId: "",
    virtual: false,
    baseDeviceId: "",
    baseModelId: 0,
    physicalDeviceId: "",
    remark: "",
    visibility: 0,
    fileName:""
};

var newDeviceModel = null;
// selected deviceId or modelId
var mark;

var modelList;
var deviceList;

function initStepBarSwitch() {
    var back =$(".prev");
    var	next = $(".next");
    var	steps = $(".step");
    var stepContents = $(".step-content");

    var startStep = 1;
    var totalStep = 4;
    var counter = startStep;

    next.bind("click", function() {
        $.each( steps, function( i ) {
            if (!$(steps[i]).hasClass('current') && !$(steps[i]).hasClass('done')) {
                $(steps[i]).addClass('current');
                $(steps[i - 1]).removeClass('current').addClass('done');
                return false;
            }
        });
        $.each( stepContents, function( i ) {
            if (!$(stepContents[i]).hasClass('current') && !$(steps[i]).hasClass('done')) {
                $(stepContents[i]).addClass('current');
                $(stepContents[i - 1]).removeClass('current').addClass('done');
                return false;
            }
        });

        if (++counter > totalStep) counter = totalStep;
        if (counter === totalStep) {
            // last step
            $("#btn-next").hide();
            $("#btn-finish").show();

            verifyDevice();
        }

    });
    back.bind("click", function() {
        $.each( steps, function( i ) {
            if ($(steps[i]).hasClass('done') && $(steps[i + 1]).hasClass('current')) {
                $(steps[i + 1]).removeClass('current');
                $(steps[i]).removeClass('done').addClass('current');
                return false;
            }
        });
        $.each( stepContents, function( i ) {
            if ($(stepContents[i]).hasClass('done') && $(stepContents[i + 1]).hasClass('current')) {
                $(stepContents[i + 1]).removeClass('current');
                $(stepContents[i]).removeClass('done').addClass('current');
                return false;
            }
        })

        if (--counter < startStep) counter = startStep;
        if (counter < totalStep) {
            $("#btn-next").show();
            $("#btn-finish").hide();
        }
    });
}

function initICheck() {
    $('.icheck-list input').on('ifClicked', function(event){
        if (this.id === "input-virtual-v") {
            $(".block").removeClass("active");
            $(".block.virtual").addClass("active");
            switchVirtual();
        } else {
            $(".block").removeClass("active");
            $(".block.physical").addClass("active");
            switchPhysical();
        }
    }).iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%'
    });
}

function switchPhysical() {
    $(".vir").hide();
    $(".phy").show();
    newDevice.virtual = false;
}

function switchVirtual() {
    $(".phy").hide();
    $(".vir").show();
    newDevice.virtual = true;
}

function generateDeviceId() {
    $.post("api/generateDeviceId", function (data) {
        newDevice.deviceId = data;
    });
}

function getModelList() {
    $.get("api/getAllModel", function (data) {
        for (var i in data) {
            var model = data[i];
            $("#modelList-content").append('<li role="presentation"><a role="menuitem" tabindex="-1"  mark="'+model.id+'">' + model.name + '</a></li>');
        }
        modelList = data;
        addDropdownClickEvent();
    });
}

function getDeviceList() {
    $.get("api/getAllDevice", function (data) {
        for (var i in data) {
            var device = data[i];
            $("#deviceList-content").append('<li role="presentation"><a role="menuitem" tabindex="-1"  mark="'+device.deviceId+'">' + device.deviceId + '</a></li>');
        }
        deviceList = data;
        addDropdownClickEvent();
    });
}

function addDropdownClickEvent() {
    $(".dropdown-menu.dynamic a").click(function () {
        var item = $(this).text();
        var dropdown = $(this).parents(".dropdown").get(0);
        $(dropdown).find(".chosenItem").text(item);
        mark = $(this).attr("mark");

        if (newDevice.virtual) {
            // virtual device
            for (var key in deviceList) {
                var device = deviceList[key];
                if (device.deviceId == mark) {
                    $("#deviceId").text(device.deviceId);
                    $("#deviceModel").text(device.model.name);
                    $("#deviceVirtualType").text(device.virtualStr);
                    $("#devicePhysicalId").text(device.physicalDeviceId);
                    $("#deviceStatus").text(device.statusStr);
                    $("#deviceType").text(device.model.typeStr);
                    $("#deviceTestType").text(device.model.testTypeStr);
                    $("#deviceRegisterTime").text(device.registerTimeStr);
                    $("#deviceRemark").text(device.remark);
                    $("#deviceConfig").text(device.config);
                    $("#device-img").attr("src", "api/image/" + device.model.imageId);

                    newDevice.baseDeviceId = mark;
                    newDeviceModel = device.model;
                    newDevice.physicalDeviceId = device.physicalDeviceId;
                }
            }
        } else {
            // physical device
            for (var key in modelList) {
                var model = modelList[key];
                if (model.id == mark) {
                    $("#modelId").text(model.id);
                    $("#modelName").text(model.name);
                    $("#modelStatus").text(model.statusStr);
                    $("#modelType").text(model.typeStr);
                    $("#modelTestType").text(model.testTypeStr);
                    $("#modelRegisterTime").text(model.registerTimeStr);
                    $("#modelRemark").text(model.remark);
                    $("#modelConfig").text(model.config);
                    $("#model-img").attr("src", "api/image/" + model.imageId);

                    newDevice.baseModelId = model.id;
                    newDeviceModel = model;
                }
            }

        }
    });
}

function initFileUpload() {
    $("#input-b1").fileinput({
        uploadAsync: true,
        uploadUrl: "./api/uploadFile",
        maxFileSize: 160000,
        allowedFileExtensions: ['xml'],
        uploadExtraData: function () {
            return {'file-name': newDevice.deviceId + ".xml"};
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

function addBtnEvent() {
    $("#input-deviceId").bind("input propertychange", function () {
        newDevice.deviceId = $(this).val();
        if (!newDevice.virtual) {
            newDevice.physicalDeviceId = newDevice.deviceId;
        }
    });

    $("#input-remark").bind("input propertychange", function () {
        newDevice.remark = $(this).val();
    });

    $("#btn-generateId").click(function () {
        $.post("api/generateDeviceId", function (data) {
            $("#input-deviceId").val(data);
            newDevice.deviceId = data;
            if (!newDevice.virtual) {
                newDevice.physicalDeviceId = newDevice.deviceId;
            }
        })
    });

    $(".dropdown-menu.fix a").click(function () {
        var item = $(this).text();
        var dropdown = $(this).parents(".dropdown").get(0);
        $(dropdown).find(".chosenItem").text(item);
        newDevice.visibility = $(this).attr("visibility");
    });

    $("#btn-finish").click(function () {
        addDevice();
    });
}

function verifyDevice() {
    $("#nDeviceId").text(newDevice.deviceId);
    $("#nDeviceRemark").text(newDevice.remark);
    $("#nDeviceFile").text(newDevice.fileName);
    $("#nDevicePhysicalId").text(newDevice.physicalDeviceId);
    $("#nDeviceBaseId").text(newDevice.baseDeviceId);
    $("#nDeviceModel").text(newDeviceModel.name);
    // $("#nDeviceModel").text(newDeviceModel);
    if (newDevice.virtual) {
        $("#nDeviceVirtualType").text("虚拟设备");
    } else {
        $("#nDeviceVirtualType").text("物理设备");
    }
    $("#nDevice-img").attr("src", "api/image/" + newDeviceModel.imageId);
    if (newDevice.visibility == 1) {
        $("#nDeviceVisibility").text("组可见");
    } else if (newDevice.visibility == 2) {
        $("#nDeviceVisibility").text("公共可见");
    } else {
        $("#nDeviceVisibility").text("私有");
    }
}

function addDevice() {
    $.post("api/addDevice", newDevice, function (data) {
        if (data.code == 200) {
            location.href = "device-info/" + newDevice.deviceId;
        } else {
            window.alert(data.message);
        }
    });
}


