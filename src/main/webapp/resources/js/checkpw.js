
/**
 * Created by Prophet on 2016/12/29.
 */
function checkpasswd(){
    var passwd = document.getElementById("password").value;
    var repasswd = document.getElementById("repeat-password").value;
    if(passwd == repasswd){
        return true;
    }else{
        window.alert("两次输入密码不一致");
        return false;
    }
}