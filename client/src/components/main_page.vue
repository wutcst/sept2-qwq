<template>
  <div class="information">
    <h3 style="text-align:left">地图</h3>
    <div id="game_table" class="game_table"></div>
    <h3 style="text-align:left">背包</h3>
    <div id="game_bag" class="game_bag">
      <ul>
        <li v-for="(item, i) in user_bag">{{i}}:{{item.name}}</li>
      </ul>
    </div>
    <div class="game_body">
      <div class="game_text">
        这里是之前的聊天内容展示，仅展示最近的十条操作/提示信息
      </div>
      <input id="enter_command" class="enter_command">
      在这里输入你的命令
    </div>
    <h4 style="text-align:left">Author:YJR SJ XZY</h4>
  </div>
</template>
<script>
export default {
  name: "main_page",
  data: function(){
    return{
      user_bag:[
        { id:1, name:"apple" },
        { id:2, name:"stone"},
      ],
    }
  }
}
</script>

<style scoped>

</style>