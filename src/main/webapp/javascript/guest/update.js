function check(obj, pwd) {
    if(obj.pwd.value != pwd) {
        alert("비밀번호가 다릅니다!");
        obj.pwd.focus();
        return false;
    }
}