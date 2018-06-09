
$(function(){
    /*获取菜单列表*/
    $.ajax({
        type: "POST",
        url: "/loginedIndexController/getMenuList",
        data: {},
        success: function(data){
            $.each(data, function(i, n) {// 加载父类节点及一级菜单
                var id = n.id;
                $('#accordion_div').accordion('add', {
                    title : n.text,
                    iconCls : "",
                    selected : true,
                    content : '<ul id="tree-'+ id + '" name="'+n.text+'" class="easyui-tree wu-side-tree"></ul>'
                });
                // 解析整个页面
                $.parser.parse();
                // 第二层生成树节点
                if (!n.children || n.children.length == 0) {
                    return true;
                }
                $("#tree-" + id).tree({
                    data : n.children,
                    cache:false,
                    //animate : true,
                    lines : true,// 显示虚线效果
                    onClick : function(node) {
                         console.dir(node);
                        if (node.attributes) {
                            var tabTitle = node.text;
                            var url = node.attributes.menu_url;
                            var iconCls="";
                            var iframe=0;
                            addTab(tabTitle, url,iconCls,iframe);
                        }
                    }
                });
            });
        }
    });
})

/**
 * Name 选项卡初始化
 */
/*$('#wu-tabs').tabs({
    tools:[{
        iconCls:'icon-reload',
        border:false,
        handler:function(){
            $('#wu-datagrid').datagrid('reload');
        }
    }]
});*/

/**
 * Name 添加菜单选项
 * Param title 名称
 * Param href 链接
 * Param iconCls 图标样式
 * Param iframe 链接跳转方式（true为iframe，false为href）
 */
function addTab(title, href, iconCls, iframe){
    var tabPanel = $('#wu-tabs');
    if(!tabPanel.tabs('exists',title)){
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
        if(iframe){
            tabPanel.tabs('add',{
                title:title,
                content:content,
                iconCls:iconCls,
                fit:true,
                cls:'pd3',
                closable:true
            });
        }
        else{
            tabPanel.tabs('add',{
                title:title,
                href:href,
                iconCls:iconCls,
                fit:true,
                cls:'pd3',
                closable:true
            });
        }
    }
    else
    {
        tabPanel.tabs('select',title);
    }
}
/**
 * Name 移除菜单选项
 */
function removeTab(){
    var tabPanel = $('#wu-tabs');
    var tab = tabPanel.tabs('getSelected');
    if (tab){
        var index = tabPanel.tabs('getTabIndex', tab);
        tabPanel.tabs('close', index);
    }
}


