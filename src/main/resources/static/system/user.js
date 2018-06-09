/**
 * Name 载入部门树
 */
$('#user-dept-tree').tree({
    url:'/userController/getDept_tree',
    onClick:function(node){
        $('#user-datagrid').datagrid('reload',{dept_id:node.id});

    }
});

/**
 * Name 载入用户数据
 */
$('#user-datagrid').datagrid({
    url:'/userController/getUserList',
    rownumbers:true,
    singleSelect:false,
    pageSize:20,
    pagination:true,
    multiSort:true,
    fitColumns:true,
    fit:true,
    columns:[[
        { checkbox:true},
        { field:'user_name',title:'用户账号',width:100,sortable:true},
        { field:'user_realname',title:'用户姓名',width:180,sortable:true},
        { field:'user_sex',title:'性别',width:180,sortable:true},
        { field:'role_name',title:'角色',width:100},
        { field:'user_phone',title:'联系电话',width:100},
        { field:'user_addr',title:'联系地址',width:100},
        { field:'dept_name',title:'所属部门',width:100},
        { field:'lastest_time',title:'最后登录时间',width:100},
        { field:'lastest_ip',title:'最后登录IP',width:100}
    ]],
    onDblClickRow:function(index, row){
        openEdit_user(row);
    }

});
/*查询*/
function goSearch() {
    var user_name=$("#search_user_name").val();
    var user_realname=$("#search_user_realname").val();
    var user_phone=$("#search_user_phone").val();
    var user_sex=$("#search_user_sex").combobox("getValue");
    var dept="";
    if($('#user-dept-tree').tree('getSelected')!=null){
        var dept_id=$('#user-dept-tree').tree('getSelected').id;
    }
    $('#user-datagrid').datagrid('reload',{user_name:user_name,user_realname:user_realname,user_phone:user_phone,user_sex:user_sex,dept_id:dept_id});
}



$('#dept_id').combotree({
    url: '/userController/getDept_tree'
});


function openAdd_user(){
    $('#user-form').form('clear');
    var item=$('#user-dept-tree').tree('getSelected');
    if(item){
        $('#dept_id').combotree('setValue',item.id);
    }

    $('#user-dialog').dialog({
        closed: false,
        modal:true,
        title: "添加",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: saveUser
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#user-dialog').dialog('close');
            }
        }]
    });
}


/**
 * Name 添加记录
 */
function saveUser(){
    $('#user-form').form('submit', {
        url:'/userController/saveUser',
        success:function(data){
            data=JSON.parse(data);
            if(data.success){
                $.messager.alert('信息提示',data.msg,'info');
                $('#user-dialog').dialog('close');
                var dept_id="";
                var item=$('#user-dept-tree').tree('getSelected');
                if(item){
                    dept_id=item.id;
                }

                $('#user-datagrid').datagrid('reload',{dept_id:dept_id});
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
function openEdit_user(row){
    var item;
    if(row){
        item=row;
    }else{
        item = $('#user-datagrid').datagrid('getSelected');
    }
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }
    $('#user-form').form('clear');
    $('#user-form').form('load', item);
    $('#user-dialog').dialog({
        closed: false,
        modal:true,
        title: "修改信息",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: saveUser
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#user-dialog').dialog('close');
            }
        }]
    });
}

/**
 * Name 删除记录
 */
function remove_user(){
    var item = $('#user-datagrid').datagrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }

    $.messager.confirm('信息提示','确定要删除该记录？', function(result){
        if(result){
            $.ajax({
                type:"POST",
                url:'/userController/delUser',
                data:{user_id:item.user_id},
                success:function(data){
                    $.messager.alert('信息提示',data.msg,'info');
                    if(data.success){
                        var dept_id="";
                        var item=$('#user-dept-tree').tree('getSelected');
                        if(item){
                            dept_id=item.id;
                        }

                        $('#user-datagrid').datagrid('reload',{dept_id:dept_id});

                    }
                }
            });
        }
    });
}

function reload_user(){
    var dept_id="";
    var item=$('#user-dept-tree').tree('getSelected');
    if(item){
        dept_id=item.id;
    }
    $('#user-datagrid').datagrid('reload',{dept_id:dept_id});
}
