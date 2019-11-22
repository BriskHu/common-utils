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
(function () {
    window.conf = {
        // 加载JS文件
        includeJs: function (src) {
            document.write('<script src="' + src + '"></script>')
        },
        // 加载JS文件
        includeJsWithCharset: function (src, charset) {
            document.write('<script src="' + src + '" charset="' + charset + '"></script>')
        },
        // 加载CSS文件
        includeCss: function (href) {
            document.write("<link href='" + href + "'/>")
        },
        jQuery: '../js/jquery-3.4.1.min.js',
        layui: '../layui/layui.js',
        layuiAll: '../layui/layui.all.js',
        contextPath: 'http://127.0.0.1:9918/dbadmin',
        utf8: 'utf-8',
    }
})();





