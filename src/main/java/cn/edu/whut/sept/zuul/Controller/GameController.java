package cn.edu.whut.sept.zuul.Controller;


import cn.edu.whut.sept.zuul.Database.RoomStore;
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
        return playerService.getPlayerInfo();
    }

    /**
     * 登录
     * @param name 名称
     * @param password 密码
     * @return id -1
     */

    @GetMapping("/player/Login")
    @ResponseBody
    public String login(String name ,String password){
        return playerService.userlogin(name, password);
    }

    /**
     * 显示物资
     * @return 物品栏
     */
    @GetMapping("/player/items")
    @ResponseBody
    public List<ItemEntity> getItemsOfPlayer() {
        return playerService.getItems();
    }
    @GetMapping("/room/getAll")
    @ResponseBody
    public List<RoomEntity> getAllRooms(){
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
        return roomService.getItemsInRoom(id);
    }

    /**
     * 四向移动
     * @param direction 东西南北
     * @return
     */

    @GetMapping("/go/{direction}")
    @ResponseBody
    public StatusResponse move(@PathVariable("direction") String direction) {
        return playerService.move(direction);
    }

    @RequestMapping("/back")
    @ResponseBody
    public StatusResponse back() {
        return playerService.back();
    }

    @RequestMapping("/take/{id}")
    @ResponseBody
    public StatusResponse take(@PathVariable("id") int id){
        return playerService.takeItem(id);
    }

    @RequestMapping("/drop/{id}")
    @ResponseBody
    public StatusResponse drop(@PathVariable("id") int id){
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
}
