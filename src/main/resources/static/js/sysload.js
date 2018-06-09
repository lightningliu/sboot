var args = new Array(
    "/easyui/1.3.4/themes/default/easyui.css",
    "/css/wu.css",
    "/css/icon.css",
    "/js/jquery-1.8.0.min.js",
    "/easyui/1.3.4/jquery.easyui.min.js",
    "/easyui/1.3.4/locale/easyui-lang-zh_CN.js"
);

//导入
importDoc(args);

function importDoc(arguments) //函数可以批量引入多个js、css
{
    for (var i = 0; i < arguments.length; i++) {
        var file = arguments[i];
        if (file.match(/.*\.js$/)){
            document.write('<script type="text/javascript" src="' + file + '"></script>');
        }
        else if (file.match(/.*\.css$/)){
            document.write('<link rel="stylesheet" href="' + file + '" type="text/css" />');
        }
    }
}

function yinru(argument) //函数可以单独引入一个js或者css
{
    var file = argument;
    if (file.match(/.*\.js$/)) //以任意开头但是以.js结尾正则表达式
    {
        document.write('<script type="text/javascript" src="' + file + '"></script>');
    } else if (file.match(/.*\.css$/)) {
        document.write('<link rel="stylesheet" href="' + file + '" type="text/css" />');
    }
}