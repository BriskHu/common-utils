/**
 * 监听地址栏变更,截取#后面内容
 * 兼容ie8+和手机端
 */
$(window).on("hashchange", function () {
    hashRoute()
});
/**
 * 页面刷新,无法监听onhashchange事件,手动执行
 */
$(function () {
    hashRoute()
})

/**
 * 路由
 */
function hashRoute() {
    var url = this.location.hash;
    if (isNull(url)) {
        // 首页默认打开第一个菜单
        url='#dingdong/management'
    }
    url = url.substring(1);
    myAjax({
        url: url
        , type: 'get'
        , contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        success: function (html) {
            $('#content').html(html)
        }
    })
}

function isNull(str) {
    if (str == undefined || str == '') {
        return true;
    }
    return false;
}

/**
 * url统一前缀
 * @param url
 */
function getUrl(url){
    return '/skill/'+url;
}

/**
 * ajax封装
 * @param options
 * @author ZuRun
 */
function myAjax(options) {
    var _this = this;
    // 默认配置
    var defaults = {
        type: 'post' 	//get、post
        , contentType: "application/json" 	// "application/json"或者用"application/x-www-form-urlencoded; charset=utf-8"
        , async: true 	// ajax是否异步
        , data: undefined
        , standardResult: true// json返回格式为:{code:0,message:'message',success:true},走标准处理流程
        , showLoading: true   // 是否显示加载动画
        , headers: {}// 请求头
        , success: function () {
        }	//成功执行事件
        , callback: function () {
        }// standardResult为true,会走此回调
        , error: function () {
        }     // 异常事件
    }
    options = $.extend(defaults, options);
    var url = options.url
        , type = options.type
        , contentType = options.contentType
        , async = options.async
        , standardResult = options.standardResult
        , data = options.data
        , showLoading = options.showLoading
        , headers = options.headers
        , callback = options.callback
        , success = options.success
        , error = options.error


    //开始发送ajax请求
    $.ajax({
        url: url,
        headers: headers,
        type: type, //静态页用get方法，否则服务器会抛出405错误
        data: contentType == "application/x-www-form-urlencoded; charset=utf-8" ? data : JSON.stringify(data),
        contentType: contentType, //必须有
        async: async,
        success: function (result, status, xhr) {
            if (standardResult && contentType == "application/json") {
                if (result.success) {
                    callback(result.data)
                } else {
                    layer.msg(result.message)
                }
            }
            success(result)
        },
        error: function (e) {
            error(e)
        }
    })
}


/**
 * 文件下载
 * @param url
 * @param fileName 文件名
 * @param data body参数
 */
function download(url, fileName, data) {

    var url = url;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);        // 也可以使用POST方式，根据接口
    xhr.responseType = "blob";    // 返回类型blob
    xhr.setRequestHeader("Content-Type", "application/json")
    // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
    xhr.onload = function () {
        // 请求完成
        if (this.status === 200) {
            // 返回200
            var blob = this.response;
            var reader = new FileReader();
            reader.readAsDataURL(blob);    // 转换为base64，可以直接放入a表情href
            reader.onload = function (e) {
                // 转换完成，创建一个a标签用于下载
                var a = document.createElement('a');
                a.download = fileName;
                a.href = e.target.result;
                $("body").append(a);    // 修复firefox中无法触发click
                a.click();
                $(a).remove();
            }
        }
    };
    // 发送ajax请求
    xhr.send(JSON.stringify(data))

}


/**
 * 获取事件,主要为了兼容firefox
 * @param event
 * @returns {*|Event}
 */
function getEvent(event) {
    return event || window.event;
}


/**
 * 无奈之举,layui的table点击事件不提供原生event事件
 */
$(document).on('mousemove', '.layui-table-view', (function (e) {
    jsonMousePosition.x = e.pageX;
    jsonMousePosition.y = e.pageY;
}));
var jsonMousePosition = {x: 0, y: 0}


var zContextMenuShow = false;
$(document).on('click', function () {
    //隐藏右键菜单
    if (zContextMenuShow) {
        $(".zContextMenu").remove()
        zContextMenuShow = false;
    }
})

/**
 * 右键菜单
 * @param option
 * @author ZuRun
 */
function zContextMenu(option) {
    var items = option.items;//==undefined?undefined:option.items;
    var event = option.event;
    var x = event == undefined ? jsonMousePosition.x : event.pageX;
    var y = event == undefined ? jsonMousePosition.y : event.pageY;


    this.createMenu = function () {
        // console.log(x)
        // console.log(y)
        /**将已打开的右键菜单关掉*/
        $(".zContextMenu").remove()
        zContextMenuShow = false;
        //延迟200ms,因为firefox的关闭菜单会把刚打开的菜单关掉
        setTimeout("zContextMenuShow=true;", 200)

        var div = document.createElement("div");
        $(div).addClass('zContextMenu');
        $(div).css('top', y);
        $(div).css('left', x);

        var div_items = document.createElement("div");
        $(div_items).addClass("items");
        $(div).append(div_items);

        $.each(items, function (index, item) {
            var div_item = document.createElement("div");
            $(div_item).addClass("item");
            $(div_item).on('click', item.click);
            $(div_item).html(item.label)
            $(div_items).append(div_item);
        })

        document.body.append(div)
    }
}
