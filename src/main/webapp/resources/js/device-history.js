/**
 * Created by Prophet on 2017/11/26.
 */
$(function () {
    listData(curPage);
    $("#jumpTo").click(function () {
        listData($("#pageNumInput").val());
    });
    $("#prevPage").click(function () {
        curPage--;
        listData(curPage);
    });
    $("#nextPage").click(function () {
        curPage++;
        listData(curPage);
    });

    bindModalNavSwitch();
});
var curPage = 1;
function listData(page) {

    $("#i-loading").show();
    $(".datashow").hide();

    $.ajax({
        type : "POST",
        url : "../api/queryDataListByPage",
        data : {
            "deviceId": deviceId,
            "page": page
        },
        success : function(data) {
            if (data.code == 200) {
                $("#dataList").find("tr:gt(0)").remove();
                $(data.datas).each(function () {
                    var tr = $("<tr><td><input type='checkbox' ></td><td></td><td ></td><td ></td><td><a class='btn btn-default btn-sm' >详细数据</a></td></tr>");
                    $("#dataList").append(tr);
                    $(tr).find("td:eq(1)").text(timeStampToDateStrMs(this.timestamp));
                    $(tr).find("td:eq(2)").text(getDirectionStr(this.direction));
                    $(tr).find("td:eq(3)").text(this.data);

                    $(tr).find("td:eq(4)").find("a")
                        .click(function () {
                            var jsonStr = $(this).parent().prev().text();
                            dataDisplay(jsonStr);
                        });

                });

                $("#pageInfo").text("总共 "+ data.totalCount + " 条记录，共 " + Math.ceil(data.totalCount / 10.0) +" 页，当前第 " + data.currentPage + " 页");
                $("#pageNumInput").val(data.currentPage);
                curPage = data.currentPage;

                $("#i-loading").hide();
                $(".datashow").show();
            } else {
                alert(data.message);
            }
        },
        dataType : "json"
    });
}

function getDirectionStr(direction) {
    if (direction == 0)
        return "device -> server";
    if (direction == 1)
        return "server -> server";
    if (direction == 2)
        return "device -> device";
    return "unknown"
}

function dataDisplay(jsonStr) {
    $("#dataRaw").text(jsonStr);
    $('#dataPrettyJson').jsonview(jsonStr);

    var obj = eval('(' + jsonStr + ')');

    $("#myModal").modal();
}

function bindModalNavSwitch() {

    $("#modal-nav li").each(function () {
        $(this).click(function () {
            $(this).siblings().each(function () {
                $(this).removeClass("active");
            })

            $(this).addClass("active");

            var contentId = $(this).attr("contentId");
            $(".modal-data-show").hide();
            $("#" + contentId).show();
        });
    })

}