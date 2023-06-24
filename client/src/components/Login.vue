<template>
    <div class="container">
        <div class="header">
            <h1 style="text-align:center">登陆</h1>
        </div>
        <div class="content">
            <div>
                <div class="quick_login">
                    <a href="">
                        <button class="QQ">
                            <use class="iconfont icon-QQ" style="color: white"></use>
                            QQ登陆
                        </button>
                    </a>
                    <br>
                    <a href="">
                        <button class="WX">
                            <use class="iconfont icon-weixin" style="color: white"></use>
                            微信登陆
                        </button>
                    </a>
                    <br>
                    <a href="">
                        <button class="ZFB">
                            <use class="iconfont icon-zhifubaorenzheng" style="color: blue"></use>
                            支付宝登陆
                        </button>
                    </a>
                </div>
            </div>
            <div>
                <div class="line">
                    <div>or</div>
                </div>
                <div class="or">或选择账号密码登陆</div>
            </div>
            <div>
                <div class="check_login">
                    <input id="1" class="user" type="text" name="username" placeholder="用户名">
                    <br>
                    <input id="2" class="pwd" type="password" name="password" placeholder="密码">
                    <br>
                    <button class="btn-1" @click="fun"> 登陆 </button>
                </div>
            </div>
        </div>
        <div class="footer">
                <br>
                <button class="to_forget" @click="$router.push('/forget')" :key="key"> 忘记密码？ </button>
                <br>
                <button class="to_register" @click="$router.push('/register' )" :key="key"> 注册 </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data:function() {
        return {
        }
    },
    methods:{
        fun() {
            var username=document.getElementById("1").value;
            var password=document.getElementById("2").value;

            if(username===""||password===""){
                alert("用户名或密码不能为空！");
                return false;
            }
            alert(username);
            axios({
                method:'get',
                url:'http://elasticsearch.natapp4.cc/player/Login?name='+username+'&password='+password,
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
        },
    },
}
</script>
    

<style>
@import "../../public/login.css";
</style>