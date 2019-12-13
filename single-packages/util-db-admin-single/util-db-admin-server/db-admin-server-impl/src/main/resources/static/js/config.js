/**
 * js 基础配置文件
 * 将所有的路径、资源加载等都放在这里，方便统一管理。
 * 在需要使用这些配置的方式通过如下方式引入即可
 * <script type="text/javascript" src="js/conf.js"></script>//先加载配置文件
 * <script type="text/javascript">
 *      conf.includeJS(conf.jQuery);//导入jQuery
 *      conf.includeCSS(conf.layuiAll);//导入layuiAll
 *      conf.contextPath
 * </script>
 */
var jQuery='../js/jquery-3.4.1.min.js';
var layui='../layui/layui.js';
var layuiAll='../layui/layui.all.js';
var selectM = "../js/layui_extends/selectM.js";
var selectN = "../js/layui_extends/selectN.js";

var contextPath='http://127.0.0.1:9918/dbadmin';
var utf8='utf-8';

includeJs(jQuery);
includeJs(layuiAll, utf8);
// includeJs(selectM);
// includeJs(selectN);


/**
 * 加载js文件
 * @param src
 * @param charset
 */
function includeJs(src, charset){
    if (charset == null){
        document.write("<script src='" + src + "'></script>");
    } else if (charset != null) {
        document.write('<script src="' + src + '" charset="' + charset + '"></script>');
    }
}

/**
 * 加载CSS文件
 * @param href
 */
function includeCss(href) {
    document.write("<link href='" + href + "'/>");
}




