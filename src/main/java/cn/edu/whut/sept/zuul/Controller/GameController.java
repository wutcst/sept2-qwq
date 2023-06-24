package cn.edu.whut.sept.zuul.Controller;

import cn.edu.whut.sept.zuul.Entity.ItemEntity;
import cn.edu.whut.sept.zuul.Entity.RoomEntity;
import cn.edu.whut.sept.zuul.Model.GetPlayerInfoResponse;
import cn.edu.whut.sept.zuul.Model.GetRoomInfoResponse;
import cn.edu.whut.sept.zuul.Model.StatusResponse;
import cn.edu.whut.sept.zuul.Service.PlayerService;
import cn.edu.whut.sept.zuul.Service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *该类用于响应前后端请求
 */
@Controller
@Slf4j
public class GameController {
    @Autowired
    RoomService roomService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    @ResponseBody
    public List<GetPlayerInfoResponse> getPlayerInfo() {
        log.info("获取玩家信息");
        return playerService.getPlayerInfo();
    }
    /**
     * 登录
     * @param name 名称
     * @param password 密码
     * @return id/-1
     */

    @GetMapping("/player/Login")
    @ResponseBody
    public StatusResponse login(String name ,String password){
        log.info("玩家登录"+name+" "+password);
        return playerService.userlogin(name, password);
    }

    /**
     * 显示物资
     * @return 物品栏
     */
    @GetMapping("/player/items")
    @ResponseBody
    public List<ItemEntity> getItemsOfPlayer() {
        log.info("获取玩家物品");
        return playerService.getItems();
    }
    @GetMapping("/room/getAll")
    @ResponseBody
    public List<RoomEntity> getAllRooms(){
        log.info("获取房间信息");
        return roomService.getAllRooms();
    }

    /**
     * 查询某个房间的信息
     * @param id 房间id
     * @return 房间信息
     */
    @GetMapping("/room/{id}")
    @ResponseBody
    public GetRoomInfoResponse getRoomInfo(@PathVariable("id") int id) {
        log.info("获取某个房间信息");
        return roomService.getRoomInfo(id);
    }

    /**
     * 查询某个房间内的物品
     * @param id 房间编号
     * @return 物品列表
     */
    @GetMapping("/room/{id}/look")
    @ResponseBody
    public List<ItemEntity> getItemsInRoom(@PathVariable("id") int id) {
        log.info("获取某房间内物品");
        return roomService.getItemsInRoom(id);
    }

    /**
     * 四向移动
     * @param direction 东西南北
     * @return
     */

    @GetMapping("/player/go/{direction}")
    @ResponseBody
    public StatusResponse move(@PathVariable("direction") String direction) {
        log.info("获取玩家信息");
        return playerService.move(direction);
    }

    @RequestMapping("/player/back")
    @ResponseBody
    public StatusResponse back() {
        log.info("回退");
        return playerService.back();
    }

    @RequestMapping("/player/take/{id}")
    @ResponseBody
    public StatusResponse take(@PathVariable("id") int id){
        log.info("抓取物品");
        return playerService.takeItem(id);
    }

    @RequestMapping("/player/drop/{id}")
    @ResponseBody
    public StatusResponse drop(@PathVariable("id") int id){
        log.info("丢弃物品");
        return playerService.dropItem(id);
    }

    /**
     *
     * @param id 物品id
     * @return 状态码
     */
    @RequestMapping("/player/eat/{id}")
    @ResponseBody
    public StatusResponse eat(@PathVariable("id") int id){
        log.info("吃掉物品");
        return playerService.eat(id);
    }
    /**
     * 退出
     */
    @RequestMapping("/quit")
    @ResponseBody
    public String quit(){
        return "退出系统！";
    }
    @RequestMapping("/player/currentWeight")
    @ResponseBody
    public double getCurrentWeight(){
        return playerService.getCurrentCarryWeight();
    }
}
