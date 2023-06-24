<template>
  <div class="body">
    <div class="information">
      <h3 style="text-align:left">地图</h3>
      tip:图中红色地点是你的当前位置，黑色的地点是可以去的地方
      <div id="game_table" class="game_table">
        <el-table :data="rowIDs" border>
          <el-table-column prop="row_id" label="行\列" align="center"></el-table-column>
          <el-table-column
              v-for="column_j in columnIDs"
              align="center"
              :key="column_j.column_id"
              :label="column_j.column_id">
            <template v-slot="scope">
              <span v-for="(i, index) in game_map" :key="index">
                <span
                    v-if="i && column_j.column_id === i.column_j &&  scope.row.row_id  === i.row_i"
                    style="margin-left: 10px">
                  <span v-if="column_j.column_id===position.now_column && scope.row.row_id === position.now_row" style="color:red">
                    <div>{{ i.name }}</div>
                  </span>
                  <span v-else>
                    <div>{{ i.name }}</div>
                  </span>
                </span>
              </span>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div class="gamer_ability" style="width:100%">
        你当前的承重能力为:{{carry_ability}}<br>
        背包内物品总重量为:{{bag_total_weight}}
      </div>
      <div id="game_bag" class="game_bag">
        <h3 style="text-align:left">背包</h3>
        <ul>
          <li v-for="(item, i) in user_bag">{{i}}:{{item.name}}<br>{{ item.description}}</li>
        </ul>
      </div>
      <div id="room_items" class="room_items">
        <h3 style="text-align:left">房间内物品</h3>
        <ul>
          <li v-for="(item, i) in room_items">id:{{i}}<br>name:{{item.name}}<br>description:{{item.description}}<br>weight:{{item.weight}}</li>
        </ul>
      </div>
    </div>
    <div class="game_body">
      <div class="show_msg">
        <ul>
          这里是之前的聊天内容展示，仅展示最近的八条操作/提示信息
          <li v-for="(msg,index) in messages" class="game_text">
            <span v-if="msg.type===0">
              System-{{msg.id}}: {{msg.msg}}
              <br><br>
            </span>
            <span v-else>
              You-{{msg.id}}: {{msg.msg}}
            </span>
          </li>
        </ul>
      </div>
      <br>
      <!--
      <input id="enter_command" class="enter_command"  type="text" v-model="new_msg" placeholder="在这里输入你的命令">
      <button class="enter_click" @click="deal_msg">提交命令</button>
      -->
      <br>

      <div class="feature">
        <button class="feature_btn" @click="btn('go')">
          前往
        </button>
        <div v-show="isClick.go">
          <button class="switch_item" v-for="(direction,index) in directions" @click="go(direction)">
            {{direction}}
            <br>
          </button>
        </div>
      </div>

      <div class="feature">
        <button class="feature_btn" @click="back">
          回退
        </button>
      </div>


      <div class="feature">
        <button class="feature_btn" @click="btn('eat')">
          吃
        </button>
        <div v-show="isClick.eat">
          <button class="switch_item" v-for="(item,index) in user_bag" @click="eat(item.name)">
            {{item.name}}
            <br>
          </button>
        </div>
      </div>

      <div class="feature">
        <button class="feature_btn" @click="btn('take')">
          拿取
        </button>
        <div v-show="isClick.take">
          <button class="switch_item" v-for="(item,index) in room_items" @click="eat(item.name)">
            {{item.name}}
            <br>
          </button>
        </div>
      </div>

      <div class="feature">
        <button class="feature_btn" @click="btn('drop')">
          丢弃
        </button>
        <div v-show="isClick.drop">
          <button class="switch_item" v-for="(item,index) in user_bag" @click="eat(item.name)">
            {{item.name}}
            <br>
          </button>
        </div>
      </div>

    </div>
    <div class="author">
      <h4 style="text-align:left">Author:YJR SJ XZY</h4>
    </div>
  </div>
</template>
<script>

import {isCancel} from "axios";

export default {
  name: "main_page",
  data: function(){
    return {
      game_map: [
        {id: 1, row_i: 0, column_j: 0, name: "出生点"},
        {id: 2, row_i: 0, column_j: -1, name: "图书馆"},
        {id: 3, row_i: 0, column_j: 1, name: "传送"},
        {id: 4, row_i: 1, column_j: -1, name: "酒吧"},
        {id: 5, row_i: 1, column_j: 0, name: "外边"},
        {id: 6, row_i: 1, column_j: 1, name: "电影院"},
        {id: 7, row_i: 2, column_j: 0, name: "实验室"},
        {id: 8, row_i: 2, column_j: 1, name: "办公室"},
      ],
      user_bag: [
        {id: 1, name: "apple1", description: "this is an apple", weight: 10},
        {id: 2, name: "stone1", description: "this is a stone", weight: 10}
      ],
      room_items:[
        {id: 1, name: "apple2", description: "this is an apple", weight: 10},
        {id: 2, name: "stone2", description: "this is a stone", weight: 10}
      ],
      messages: [
        {id: 1, msg: "apple", type:1},
        {id: 2, msg: "this is an apple", type:0}
      ],
      new_msg: "",
      msg_num: 2,
      nextID: 3,
      rowIDs: [
        {row_id: 0},
        {row_id: 1},
        {row_id: 2}
      ],
      columnIDs: [
        {column_id: -1},
        {column_id: 0},
        {column_id: 1}
      ],
      position: {now_row: 0, now_column: 0},
      carry_ability:100,
      bag_total_weight:20,
      isClick:{
        eat: false,
        take: false,
        drop: false,
        go: false,
      },
      directions:[
          "south","east"
      ]
    }
  },
  methods:{
    deal_msg(){
      if(this.new_msg !== ""){
        this.messages.push({id:this.nextID,msg:this.new_msg,type:1})
        this.nextID++
        this.messages.push({id:this.nextID,msg:"answer",type:0})

        this.new_msg=""
        this.msg_num+=2
        if(this.msg_num>16){
          this.messages.splice(0,2)
        }
      }
    },
    btn(chs){
      if(chs==="eat") this.isClick.eat=!this.isClick.eat;
      if(chs==="take") this.isClick.take=!this.isClick.take;
      if(chs==="drop") this.isClick.drop=!this.isClick.drop;
      if(chs==="go") this.isClick.go=!this.isClick.go;
    },
    eat(name){
      alert("eat "+name);
    },
    take(name){
      alert("take "+name);
    },
    drop(name){
      alert("drop "+name);
    },
    go(direction){
      alert("go "+direction);
    },
    back(){
      alert("back")
    }
  }
}
</script>

<style scoped>
.body{
  width: 1500px;
  height: 780px;
}
.information{
  float: left;
  width: 600px;
  height: 800px;
}
.game_body{
  margin-left: 20px;
  float: left;
  width: 800px;
  height: 780px;
}
.gamer_ability{
  float: left;
  margin-top:20px;
  width: 50%;
}
.game_bag{
  float: left;
  width: 50%;
}
.room_items{
  float: left;
  width: 50%;
}
.show_msg{
  height: 500px;
  border-color: black;
  border-width: 2px;
}

.author{
  float: left;
}

.feature{
  float: left;
  height: 100px;
  width: 60px;
}

.switch_item,
.feature_btn{
  margin-top: 5px;
  border:none;
  height: 40px;
  width: 50px;
  border-radius: 5px;
  margin-left: 20px;
}

</style>