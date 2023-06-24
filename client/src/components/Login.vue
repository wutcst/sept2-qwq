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
                <button class="to_forget" @click="$router.push('/forget')" > 忘记密码？ </button>
                <br>
                <button class="to_register" @click="$router.push('/register' )" > 注册 </button>
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
    

<style scoped>
.body {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
    background-color: whitesmoke;
    background-position: center;
    background-size:100%;
    overflow-y: hidden;
}

.a{
    text-decoration: none;
    color:white;
}

.container{
    margin-top:5%;
    padding: 0;
    height: 95%;
    width: 100%;
    overflow-y: hidden;
}
.main_container{
    padding: 0;
    height: 100%;
    width: 100%;
    overflow-y: hidden;
}
.header{
    height: 10%;
}
.main_header{
    height: 10%;
    background-color: lightblue;
}
.content{
    margin-top: 3%;
    height: 300px;
    width: 100%;
    clear:both;
}
.main_content{
    height: 85%;
    width: 100%;
    clear:both;
}
.footer{
    height: 500px;
    text-align: center;
}
.main_footer{
    height: 20%;
    text-align: center;
    background-color: lightblue;
}
.quick_login{
    float: left;
    height: 70%;
    width: 40%;
    text-align: center;
}
.check_login{
    float: left;
    height: 70%;
    width: 40%;
    text-align: center;
}
.QQ{
    width: 260px;
    color: white;
    background-color: black;
    border: none;
    height: 40px;
    border-radius: 5px;
    left: 30%;
    margin-top: 10px;
}
.WX{
    width: 260px;
    color: white;
    background-color: #09bb07;
    border: none;
    height: 40px;
    border-radius: 5px;
    left: 30%;
    margin-top: 10px;
}
.ZFB{
    width: 260px;
    color: black;
    background-color: white;
    border: black;
    height: 40px;
    border-radius: 5px;
    left: 30%;
    margin-top: 10px;
}
.line{
    height: 70%;
    width: 20%;
    float: left;
    text-align: center;
}
.line div {
    border: 3px solid black;
    color: black;
    border-radius: 100%;
    text-align: center;
    margin-top: 20%;
    width: 22px;
    background-color: white;
    margin-left: 45%;
}

.or{
    float:left;
    margin-left:165px;
    margin-top: 20px;
    margin-bottom: 20px;
}
.new_user,
.new_pwd,
.new_birth,
.new_mail,
.check_pwd,
.user,
.pwd,
.new_pwd,
.mail,
.date{
    margin-top: 10px;
    border:none;
    height: 40px;
    width: 260px;
    border-radius: 5px;
}

.btn-1,
.btn-2{
    margin-top: 5px;
    border:none;
    background-color: dodgerblue;
    height: 40px;
    width: 260px;
    border-radius: 5px;
}
.btn-1:active,
.btn-2:active{
    background-color: blue;
}

.to_login{
    border: none;
    height: 35px;
    width: 300px;
    border-radius: 5px;
    background-color:dodgerblue;
    margin-top:20px;
}

.to_forget,
.to_register{
    border: none;
    height: 35px;
    width: 300px;
    border-radius: 5px;
    background-color:dodgerblue;
    margin-top:20px;
}

.split{
    width: 10%;
    float:left;
    text-align: center;
}
.register,
.reset{
    width: 100%;
    text-align: center;
}

.menu{
    width: 20%;
    height: 100%;
    background-color: orange;
}

@media screen and (max-width: 650px){
    .line,.menu {visibility: hidden;}
    .sub {width: 100%;}
    .QQ,.WX,.ZFB,.user,.pwd,.mail,.btn-1{
        margin-left:120px;
    }
}

@media screen and (min-width: 650px){
    .or {display: none}
    .sub {width: 50%;}
}
</style>