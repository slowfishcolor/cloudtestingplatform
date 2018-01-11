/**
 * Created by Prophet on 2017/12/31.
 */
$(function () {
    initICheck();
    initStepBarSwitch();
});
var newDevice = {
    deviceId: ""
}
function initStepBarSwitch() {
    var back =$(".prev");
    var	next = $(".next");
    var	steps = $(".step");
    var stepContents = $(".step-content");

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
    });
    back.bind("click", function() {
        $.each( steps, function( i ) {
            if ($(steps[i]).hasClass('done') && $(steps[i + 1]).hasClass('current')) {
                $(steps[i + 1]).removeClass('current');
                $(steps[i]).removeClass('done').addClass('current');
                return false;
            }
        })
        $.each( stepContents, function( i ) {
            if ($(stepContents[i]).hasClass('done') && $(stepContents[i + 1]).hasClass('current')) {
                $(stepContents[i + 1]).removeClass('current');
                $(stepContents[i]).removeClass('done').addClass('current');
                return false;
            }
        })
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

}

function switchVirtual() {

}

function generateDeviceId() {
    $.post("api/generateDeviceId", function (data) {
        newDevice.deviceId = deviceId;
    });
}

function getModelList() {

}

function getPhysicalDeviceList() {

}
