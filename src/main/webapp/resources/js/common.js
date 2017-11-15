/**
 * Created by Prophet on 2017/11/15.
 */
// var Alert, Confirm;
// (function () {
//     var modal,
//         Proxy = function (isAlert) {
//             return function () {
//                 if (arguments.length != 1) return;
//                 var msg = typeof arguments[0] === 'string' && arguments[0] || arguments[0].msg || '',
//                     onOk = typeof arguments[0] === 'object' && typeof arguments[0].onOk === 'function' && arguments[0].onOk,
//                     onCancel = typeof arguments[0] === 'object' && typeof arguments[0].onCancel === 'function' && arguments[0].onCancel,
//                     width = typeof arguments[0] === 'object' && arguments[0].width || 400,
//                     _onModalShow = function () {
//                         this.setWidth(width);
//                         this.setContent(msg);
//                         this[(isAlert ? 'hide' : 'show') + 'Button']('.btn-cancel');
//                     },
//                     _onModalHide = function () {
//                         this.setContent('');
//                     };
//
//                 //延迟初始化modal
//                 if(!modal) {
//                     modal = new Modal({
//                         'title': '操作提示',
//                         onModalShow: _onModalShow,
//                         onModalHide: _onModalHide,
//                         onContentReady: function(){
//                             this.$modalBody.css({
//                                 'padding-top': '30px',
//                                 'padding-bottom': '30px'
//                             })
//                         }
//                     });
//                 } else {
//                     var $modal = modal.$modal;
//                     //如果modal已经初始化则需要重新监听事件
//                     $modal.off('modalShow modalHide');
//                     $modal.off('modalShow modalHide');
//                     $modal.on('modalShow', $.proxy(_onModalShow, modal));
//                     $modal.on('modalHide', $.proxy(_onModalHide, modal));
//                 }
//
//                 modal.setOptions({
//                     onOk: onOk || $.noop,
//                     onCancel: onCancel || $.noop
//                 });
//
//                 modal.open();
//
//                 $("#alert").modal();
//             }
//         };
//
//     Alert = Proxy(true);
//     Confirm = Proxy();
// })();

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