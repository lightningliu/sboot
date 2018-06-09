/*左侧菜单项*/
$('#lkh_menu').treegrid({
    fitColumns:true,
    url:'/menuController/getList',
    idField:'menu_id',
    treeField:'menu_name',
    columns:[[
        {title:'菜单名称',field:'menu_name',width:200},
        {title:'菜单地址',field:'menu_url',width:200},
        {title:'显示顺序',field:'menu_order',width:200},
         {title:'操作',field:'op',width:200,
             formatter:function(value,row,index){
                 return "<a href='javascript:void(0);' onclick=\"permissionManage('"+row.menu_id+"')\">[权限管理]</a>";
             }
         }
    ]],
    onClickRow:function(row){
        $('#lkh_permission_datagrid').datagrid('load',{menu_id:row.menu_id});
    },
    onDblClickRow:function(row){
        openMenuEdit(row);
    }
});

function permissionManage(menu_id){
    $('#lkh_permission_datagrid').datagrid('load',{menu_id:menu_id});
    $("#layout").layout("expand", "east");
}
/**
 * Name 打开添加窗口
 */
$('#menu_parent_id').combotree({
    url: '/menuController/getMenuList_combotree'
});

function openMenuAdd(){
    $('#menu-form').form('clear');
    var item = $('#lkh_menu').treegrid('getSelected');
    if(item){
        $('#menu_parent_id').combotree('setValue',item.menu_id);
    }
    $('#menu-dialog').dialog({
        closed: false,
        modal:true,
        title: "添加",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: save
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#menu-dialog').dialog('close');
            }
        }]
    });
}


/**
 * Name 添加记录
 */
function save(){
    $('#menu-form').form('submit', {
        url:'/menuController/saveMenu',
        success:function(data){
            data=JSON.parse(data);
            if(data.success){
                $.messager.alert('信息提示',data.msg,'info');
                $('#menu-dialog').dialog('close');
                $('#lkh_menu').treegrid('reload');
            }
            else
            {
                $.messager.alert('信息提示',data.msg,'info');
            }
        }
    });
}


/**
 * Name 打开修改窗口
 */
function openMenuEdit(row){
    var item;
    if(row){
        item=row;
    }else{
        item = $('#lkh_menu').treegrid('getSelected');
    }
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }
    $('#menu-form').form('clear');
    $('#menu-form').form('load', item);
    $('#menu-dialog').dialog({
        closed: false,
        modal:true,
        title: "修改信息",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: save
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#menu-dialog').dialog('close');
            }
        }]
    });
}

/**
 * Name 删除记录
 */
function removeMenu(){
    var item = $('#lkh_menu').treegrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }

    $.messager.confirm('信息提示','确定要删除该记录？', function(result){
        if(result){
            $.ajax({
                type:"POST",
                url:'/menuController/delMenu',
                data:{menu_id:item.menu_id},
                success:function(data){
                    $.messager.alert('信息提示',data.msg,'info');
                    if(data.success){
                        $('#lkh_menu').treegrid('reload');
                    }
                }
            });
        }
    });
}

function reloadMenu(){
    $('#lkh_menu').treegrid('reload');
}


/**
 * Name 载入数据
 */
$('#lkh_permission_datagrid').datagrid({
    url:'/menuController/getPermissionList',
    rownumbers:true,
    singleSelect:false,
    pageSize:20,
    pagination:true,
    multiSort:true,
    fitColumns:true,
    fit:true,
    columns:[[
        { checkbox:true},
        { field:'perm_name',title:'权限名称',width:300},
        { field:'perm_flag',title:'权限标记',width:300}
    ]],
    onDblClickRow:function(index, row){
        openEdit_perm(row);
    }
});



function openAdd_perm(){
    $('#perm-form').form('clear');

    var item=$('#lkh_menu').treegrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的菜单记录",'info');
        return;
    }
    $("#menu_id").val(item.menu_id);
    $('#perm-dialog').dialog({
        closed: false,
        modal:true,
        title: "添加",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: savePerm
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#perm-dialog').dialog('close');
            }
        }]
    });
}


/**
 * Name 添加记录
 */
function savePerm(){
    $('#perm-form').form('submit', {
        url:'/menuController/savePerm',
        success:function(data){
            data=JSON.parse(data);
            if(data.success){
                $.messager.alert('信息提示',data.msg,'info');
                $('#perm-dialog').dialog('close');
                $('#lkh_permission_datagrid').datagrid('reload',{menu_id:$('#lkh_menu').treegrid('getSelected').menu_id});
            }
            else
            {
                $.messager.alert('信息提示',data.msg,'info');
            }
        }
    });
}


/**
 * Name 打开修改窗口
 */
function openEdit_perm(row){
    var item;
    if(row){
        item=row;
    }else{
        item = $('#lkh_permission_datagrid').datagrid('getSelected');
    }
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }
    $('#perm-form').form('clear');
    $('#perm-form').form('load', item);
    $('#perm-dialog').dialog({
        closed: false,
        modal:true,
        title: "修改信息",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: savePerm
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#perm-dialog').dialog('close');
            }
        }]
    });
}

/**
 * Name 删除记录
 */
function remove_perm(){
    var item = $('#lkh_permission_datagrid').datagrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }

    $.messager.confirm('信息提示','确定要删除该记录？', function(result){
        if(result){
            $.ajax({
                type:"POST",
                url:'/menuController/delPerm',
                data:{perm_id:item.perm_id},
                success:function(data){
                    $.messager.alert('信息提示',data.msg,'info');
                    if(data.success){
                        $('#lkh_permission_datagrid').datagrid('reload',{menu_id:$('#lkh_menu').treegrid('getSelected').menu_id});
                    }
                }
            });
        }
    });
}

function reloadMenu(){
    $('#lkh_permission_datagrid').datagrid('reload');
}

