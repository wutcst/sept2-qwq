<template>
    <div class="container">
  <div class="header">
    <h1 style="text-align:center">重置密码</h1>
  </div>
  <div class="content">
    <div>
      <div class="reset">
        <input id="1" class="new_user" type="text" name="username" placeholder="用户名">
        <br>
        <input id="2" class="new_mail" type="text" name="mail" placeholder="邮箱">
        <br>
        <input id="3" class="new_pwd" type="password" name="password" placeholder="新密码">
        <br>
        <input id="4" class="check_pwd" type="password" name="new_password" placeholder="确认密码">
        <br>
        <button class="btn-2" @click="check"> 提交 </button>
      </div>
    </div>
  </div>
  <div class="footer">
      <button class="to_login" @click="$router.push('/login')"> 回到登陆界面 </button>
  </div>
</div>
</template>

<script>
export default {
    data: function(){
        return 
    },
    methods: {
        check() {
            var username=document.getElementById("1").value;
            var password=document.getElementById("3").value;
            axios({
                method:'get',
                url:'http://elasticsearch.natapp4.cc/player/Login?name=',
            }).then((res)=>{
                if(res.data.status==="成功"){
                    alert("登陆成功");
                    this.$router.push('/mainpage');
                }
                else {
                    alert("用户名或密码错误");
                    return false;
                }
            });
        }
    }
}
</script>