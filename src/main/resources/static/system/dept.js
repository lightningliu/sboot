$('#lkh_dept').treegrid({
    fitColumns:true,
    url:'/deptController/getDeptList_gridtree',
    idField:'dept_id',
    treeField:'dept_name',
    columns:[[
        {title:'部门名称',field:'dept_name',width:200},
        {title:'显示顺序',field:'dept_order',width:200}
    ]],
    onDblClickRow:function(row){
        openEdit_dept(row);
    }
});

/**
 * Name 打开添加窗口
 */
$('#parent_dept_id').combotree({
    url: '/deptController/getDeptList_combotree'
});

function openAdd_dept(){
    $('#dept-form').form('clear');
    var item = $('#lkh_dept').treegrid('getSelected');
    if(item){
        $('#parent_dept_id').combotree('setValue',item.dept_id);
    }
    $('#dept-dialog').dialog({
        closed: false,
        modal:true,
        title: "添加",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: save_dept
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dept-dialog').dialog('close');
            }
        }]
    });
}


/**
 * Name 添加记录
 */
function save_dept(){
    $('#dept-form').form('submit', {
        url:'/deptController/saveDept',
        success:function(data){
            data=JSON.parse(data);
            if(data.success){
                $.messager.alert('信息提示',data.msg,'info');
                $('#dept-dialog').dialog('close');
                $('#lkh_dept').treegrid('reload');
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
function openEdit_dept(row){
    var item;
    if(row){
        item=row;
    }else{
        item = $('#lkh_dept').treegrid('getSelected');
    }
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }
    $('#dept-form').form('clear');
    $('#dept-form').form('load', item);
    $('#dept-dialog').dialog({
        closed: false,
        modal:true,
        title: "修改信息",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: save_dept
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#dept-dialog').dialog('close');
            }
        }]
    });
}

/**
 * Name 删除记录
 */
function remove_dept(){
    var item = $('#lkh_dept').treegrid('getSelected');
    if(!item){
        $.messager.alert('信息提示',"请先选择操作的记录",'info');
        return;
    }

    $.messager.confirm('信息提示','确定要删除该记录？', function(result){
        if(result){
            $.ajax({
                type:"POST",
                url:'/deptController/delDept',
                data:{dept_id:item.dept_id},
                success:function(data){
                    $.messager.alert('信息提示',data.msg,'info');
                    if(data.success){
                        $('#lkh_dept').treegrid('reload');
                    }
                }
            });
        }
    });
}

function reload_dept(){
    $('#lkh_dept').treegrid('reload');
}

