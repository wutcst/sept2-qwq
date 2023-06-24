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
              :label="column_j.column_id.toString()">
            <template v-slot="scope">
              <span v-for="(i, index) in game_map" :key="index">
                <span
                    v-if="i && column_j.column_id === i.location.x &&  scope.row.row_id  === i.location.y"
                    style="margin-left: 10px">
                  <span v-if="i.id===cur_room_id" style="color:red">
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
              System: {{msg.msg}}
              <br><br>
            </span>
            <span v-else>
              You: {{msg.msg}}
            </span>
          </li>
        </ul>
      </div>
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
          <button class="switch_item" v-for="(item,index) in user_bag" @click="eat(item.name,item.id)">
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
          <button class="switch_item" v-for="(item,index) in room_items" @click="take(item.name,item.id)">
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
          <button class="switch_item" v-for="(item,index) in user_bag" @click="drop(item.name,item.id)">
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

import axios from "axios";

export default {
  created() {
    axios({
      method:'get',
      url:'http://elasticsearch.natapp4.cc/room/getAll',
    }).then((res)=>{
      //console.log(res.data.data);
      this.game_map=res.data;
    });
    this.update_weight();

    this.update_position();
    this.update_room_items();
    this.update_user_bag();
  },
  name: "main_page",
  data: function(){
    return {
      user_id: 1,
      game_map: [
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
        {id: 1, msg: "Game Start", type:1},
        {id: 2, msg: "Welcome to World of Zuue!", type:0}
      ],
      msg_num: 2,
      nextID: 3,
      rowIDs: [
        {row_id: 1},
        {row_id: 0},
        {row_id: -1}
      ],
      columnIDs: [
        {column_id: -1},
        {column_id: 0},
        {column_id: 1}
      ],
      cur_room_id: 1,
      carry_ability:100,
      bag_total_weight:20,
      isClick:{
        eat: false,
        take: false,
        drop: false,
        go: false,
      },
      directions:[
          "north","south"
      ]
    }
  },
  methods:{
    btn(chs){
      if(chs==="eat") this.isClick.eat=!this.isClick.eat;
      if(chs==="take") this.isClick.take=!this.isClick.take;
      if(chs==="drop") this.isClick.drop=!this.isClick.drop;
      if(chs==="go") this.isClick.go=!this.isClick.go;
    },
    eat(name,id){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/eat/'+id,
      }).then((res)=>{
        this.push_msg("eat "+name,1);
        if(res.data.status==="食用成功"){
          this.push_msg("吃掉啦",0);
          this.update_user_bag();
          this.update_room_items();
          this.update_weight();
        }else{
          this.push_msg(res.data.status,0);
        }
      });
    },
    take(name,id){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/take/'+id,
      }).then((res)=>{
        this.push_msg("take "+name,1);
        if(res.data.status==="成功"){
          this.push_msg("拿到啦 ",0);
          this.update_user_bag();
          this.update_room_items();
          this.update_weight();
        }else{
          this.push_msg(res.data.status,0);
        }
      });
    },
    drop(name,id){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/drop/'+id,
      }).then((res)=>{
        this.push_msg("drop "+name,1);
        if(res.data.status==="成功"){
          this.push_msg("丢掉啦 ",0);
          this.update_user_bag();
          this.update_room_items();
          this.update_weight();
        }else{
          this.push_msg(res.data.status,0);
        }
      });
    },
    go(direction){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/go/'+direction,
      }).then((res)=>{
        this.push_msg("go "+direction,1);
        if (res.data.status==="成功"){
          this.update_position();
        }else{
          this.push_msg(res.data.status,0);
        }
      });
    },
    back(){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/back',
      }).then((res)=>{
        this.push_msg("back",1);
        if (res.data.status==="成功"){
          this.update_position();
        }else {
          this.push_msg(res.data.status,0);
        }
      });
    },
    update_position(){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player',
      }).then((res)=>{
        this.cur_room_id=res.data[0].currentRoomId;
        this.show_now_room();
        this.update_direction();
        this.update_room_items();
      })
    },
    show_now_room(){
      for(let i=0;i<this.game_map.length;i++){
        if(this.game_map[i].id===this.cur_room_id){
          this.push_msg(this.game_map[i].description,0);
          break;
        }
      }
    },
    update_direction(){
      for(let i=0;i<this.game_map.length;i++){
        if(this.game_map[i].id===this.cur_room_id){
          this.directions=Object.keys(this.game_map[i].exitRoomIdMap);
          break;
        }
      }
    },
    update_room_items(){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/room/'+this.cur_room_id+'/look',
      }).then((res)=>{
        this.room_items=res.data;
      })
    },
    update_user_bag(){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player/items',
      }).then((res)=>{
        this.user_bag=res.data;
      })
    },
    push_msg(user_cmd,type){
      this.messages.push({id:this.nextID,msg:user_cmd,type:type})
      this.nextID++
      this.msg_num++
      if(this.msg_num>16){
        this.messages.splice(0,1)
      }
    },
    update_weight(){
      axios({
        method:'get',
        url:'http://elasticsearch.natapp4.cc/player',
      }).then((res)=>{
        this.carry_ability=res.data[0].maxCarryWeight;
        this.bag_total_weight=res.data[0].currentCarryWeight;
      });
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