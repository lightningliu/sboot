function login() {
    var user_name=$("#user_name").val();
    var user_pwd=$("#user_pwd").val();

    if(user_name==null || user_name=='' ){
        alert("请输入用户名");
        return;
    }
    if(user_pwd==null || user_pwd=='' ){
        alert("请输入密码");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/login",
        data: {user_name:user_name,user_pwd:user_pwd},
        dataType:"json",
        success: function(data){

            if(data.success){
                location.href="/loginedIndexController/index";
            }else{
                alert(data.msg);
            }

        }
    });

}


$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        login();
    }
});