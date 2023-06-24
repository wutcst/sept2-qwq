<template>
<div class="container">
  <div class="header">
    <h1 style="text-align:center">注册</h1>
  </div>
  <div class="content">
    <div>
      <div class="register">
        <input id="1" class="new_user" type="text" name="username" placeholder="用户名">
        <br>
        <input id="2" class="new_pwd" type="password" name="password" placeholder="密码">
        <br>
        <input id="3" class="new_mail" type="text" name="mail" placeholder="邮箱">
        <br>
        <input id="4" class="new_birth" type="date" name="birth">
        <br>
        <button class="btn-2" @click="check"> 注册 </button>
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
  data() {
    return {
    };
  },
  methods: {
    check() {
        alert("注册成功");
        this.$router.push('/login');
    }
  }
};
</script>s