/**
 * 载入角色列表数据
 */
$('#role_datagrid').datagrid({
    url:'/roleController/getRoleList',
    rownumbers:true,
    singleSelect:false,
    pageSize:20,
    pagination:true,
    multiSort:true,
    fitColumns:true,
    fit:true,
    columns:[[
        { checkbox:true},
        { field:'role_name',title:'角色名称',width:300},
        { field:'role_note',title:'角色备注',width:300},
        {title:'操作',field:'op',width:500,
            formatter:function(value,row,index){
                return "<a href='javascript:void(0);' onclick=\"permissionManage('"+row.role_id+"')\">[分配权限]</a>";
            }
        }
    ]],
    onDblClickRow:function(index, row){
        openEdit_role(row);
    }
});



function openAdd_role(){
    $('#role_form').form('clear');
    $('#role_dialog').dialog({
        closed: false,
        modal:true,
        title: "添加",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: saveRole
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#role_dialog').dialog('close');
            }
        }]
    });
}


/**
 * 打开修改窗口
 */
function openEdit_role(row){
    var item;
    if(row){
        item=row;
    }else{
        item = $('#role_datagrid').datagrid('getSelected');
    }
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }
    $('#role_form').form('clear');
    $('#role_form').form('load', item);
    $('#role_dialog').dialog({
        closed: false,
        modal:true,
        title: "修改信息",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: saveRole
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#role_dialog').dialog('close');
            }
        }]
    });
}


/**
 * 添加记录
 */
function saveRole(){
    $('#role_form').form('submit', {
        url:'/roleController/saveRole',
        success:function(data){
            data=JSON.parse(data);
            if(data.success){
                $.messager.alert('信息提示',data.msg,'info');
                $('#role_dialog').dialog('close');
                $('#role_datagrid').datagrid('reload');
            }
            else
            {
                $.messager.alert('信息提示',data.msg,'info');
            }
        }
    });
}

/**
 * 删除记录
 */
function remove_role(){
    var item = $('#role_datagrid').datagrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }

    $.messager.confirm('信息提示','确定要删除该记录？', function(result){
        if(result){
            $.ajax({
                type:"POST",
                url:'/roleController/delRole',
                data:{role_id:item.role_id},
                success:function(data){
                    $.messager.alert('信息提示',data.msg,'info');
                    if(data.success){
                        $('#role_datagrid').datagrid('reload');
                    }
                }
            });
        }
    });
}

function reload_role(){
    $('#role_datagrid').datagrid('reload');
}



function  permissionManage(role_id) {
    $('#role_permission_tree').tree({
        url:'/roleController/getMenuAndPerssionList_tree?role_id='+role_id,
        checkbox:true
    });
    $("#role_layout").layout("expand", "east");

}




//获取权限tree
$('#role_permission_tree').tree({
    url:'/roleController/getMenuAndPerssionList_tree',
    checkbox:true
});
//保存权限
function save_permission() {
    var item = $('#role_datagrid').datagrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的角色记录",'info');
        return;
    }
    var nodes = $('#role_permission_tree').tree('getChecked', ['checked','indeterminate']);
    // if(nodes.length<=0){
    //     $.messager.alert('信息提示',"请先选择操作的角色记录",'info');
    // }
    var menus="";
    var permissions="";
    var menuArray= new Array();
    var permissionArray= new Array();
    for(var i=0;i<nodes.length;i++){
        if(nodes[i].attributes.type=='1'){//菜单
            menuArray.push(nodes[i].id);
        }else{//权限
            permissionArray.push(nodes[i].id);
        }
    }
    menus=menuArray.join();
    permissions=permissionArray.join();
    $.ajax({
        type: "POST",
        url: "/roleController/savePermission",
        data: {menus:menus,permissions:permissions,role_id:item.role_id},
        success: function(data){
            $.messager.alert('信息提示',data.msg,'info');
        }
    });


}



function reload_permission(){
    var role_id="";
    var item = $('#role_datagrid').datagrid('getSelected');
    if(item){
        role_id=item.role_id;
    }
    $('#role_permission_tree').tree({
        url:'/roleController/getMenuAndPerssionList_tree?role_id='+role_id,
        checkbox:true
    });
}
