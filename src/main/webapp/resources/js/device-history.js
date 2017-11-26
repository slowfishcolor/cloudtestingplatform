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
                            $("#dataRaw").text(jsonStr);
                            $("#myModal").modal();
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