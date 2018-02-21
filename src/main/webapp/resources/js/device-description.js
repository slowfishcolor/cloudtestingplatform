/**
 * Created by Prophet on 2018/1/19.
 */
$(function() {
    var extractor = new Xsd2Json("CMfgTestInstrument.xsd", {"schemaURI":"../resources/atml/"});

    $("#xml_editor").xmlEditor({
        ajaxOptions: {
            xmlRetrievalPath: "../api/file/" + deviceId + ".xml",
            xmlUploadPath: "../api/uploadXml/" + deviceId,
            submitResponseHandler: new function (jqXHR, exception) {

            }
        },
        elementUpdated: hideLoading,
        confirmExitWhenUnsubmitted : false,
        submitButtonConfigs : [
            {
                url : "../api/uploadXml/" + deviceId,
//                                responseHandler : myResponseHandler,
                label : "保存",
                cssClass: "btn btn-primary mg-r mg-b mg-t"
            },
            {
                label : "重置",
                onSubmit : function() {
                    // do work
                },
                cssClass: "btn btn-default mg-r mg-b mg-t"
            }
        ],
        schema: extractor.getSchema()
    });
});
function hideLoading() {
    $(".loading").hide();
}
