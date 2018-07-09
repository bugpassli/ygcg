<script>
  window.onload = function(){
    var oForm = document.getElementById('loginForm');
    var oUser = document.getElementById('exampleInputName2');
    var oPswd = document.getElementById('inputPassword3');
    var oRemember = document.getElementById('checkbox3');
    //页面初始化时，如果帐号密码cookie存在则填充
    if(getCookie('exampleInputName2') && getCookie('inputPassword3')){
      oUser.value = getCookie('exampleInputName2');
      oPswd.value = getCookie('inputPassword3');
      oRemember.checked = true;
    }
    //复选框勾选状态发生改变时，如果未勾选则清除cookie
    oRemember.onchange = function(){
      if(!this.checked){
        delCookie('exampleInputName2');
        delCookie('inputPassword3');
      }
    };
    //表单提交事件触发时，如果复选框是勾选状态则保存cookie
    oForm.onsubmit = function(){
      if(remember.checked){ 
        setCookie('exampleInputName2',oUser.value,7); //保存帐号到cookie，有效期7天
        setCookie('inputPassword3',oPswd.value,7); //保存密码到cookie，有效期7天
      }
    };
  };
  //设置cookie
  function setCookie(name,value,day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires='+ date;
  };
  //获取cookie
  function getCookie(name){
    var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
      return arr[1];
    }else{
      return '';
    }
  };
  //删除cookie
  function delCookie(name){
    setCookie(name,null,-1);
  };
</script>