package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.ItemEntity;
import cn.edu.whut.sept.zuul.Entity.PlayerEntity;
import cn.edu.whut.sept.zuul.Entity.RoomEntity;
import cn.edu.whut.sept.zuul.Database.ItemStore;
import cn.edu.whut.sept.zuul.Model.GetPlayerInfoResponse;
import cn.edu.whut.sept.zuul.Model.Status;
import cn.edu.whut.sept.zuul.Model.StatusResponse;
import cn.edu.whut.sept.zuul.Database.PathStore;
import cn.edu.whut.sept.zuul.Database.PlayerStore;
import cn.edu.whut.sept.zuul.Database.RoomStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 完成功能设计
 */
@Service
public class PlayerService {
    private static final String ownerTypePlayer = "Player";
    private static final String ownerTypeRoom = "Room";

    @Autowired
    private PlayerStore playerStore;

    @Autowired
    private RoomStore roomStore;

    @Autowired
    private ItemStore itemStore;

    @Autowired
    private PathStore pathStore;

    /**
     * 得到当前玩家的重量
     * @return 当前玩家的重量
     */
    public double getCurrentCarryWeight() {
        PlayerEntity player = playerStore.getPlayerEntity();

        List<ItemEntity> items = itemStore.getItemsByOwner(ownerTypePlayer, player.getId());

        double currentCarryWeight = 0;
        for (ItemEntity item : items) {
            currentCarryWeight += item.getWeight();
        }

        return currentCarryWeight;
    }

    /**
     * 获取玩家用户名密码
     * @return 有就是id，没有就是-1
     */
    public String userlogin(String username,String password) {
        PlayerEntity player = playerStore.getPlayerEntity();
        if (Objects.equals(username, player.getName())
                && Objects.equals(password, player.getPassword())){
            return String.valueOf(player.getId());
        }
        else
            return "-1";
    }

    /**
     * 得到玩家的信息
     * @return 玩家的信息
     */
    public List<GetPlayerInfoResponse> getPlayerInfo() {
        PlayerEntity player = playerStore.getPlayerEntity();
        List<GetPlayerInfoResponse> lst =new ArrayList<>();
        lst.add(new GetPlayerInfoResponse(
                player.getName(),
                getCurrentCarryWeight(),
                player.getMaxCarryWeight(),
                player.getCurrentRoomId(),
                roomStore.getRoom(player.getCurrentRoomId()).getName()));
        return lst;
    }

    /**
     * 拿取物品
     * @param id 物品的id
     * @return 拿取的结果
     */
    public StatusResponse takeItem(int id) {
        PlayerEntity player = playerStore.getPlayerEntity();

        ItemEntity item = itemStore.getItem(id);

        if (item.getOwnerType().equals(ownerTypePlayer)) {
            return new StatusResponse(Status.NotInCurrentRoom);
        }

        if (item.getOwnerId() != player.getCurrentRoomId()) {
            return new StatusResponse(Status.NotInCurrentRoom);
        }

        if (getCurrentCarryWeight() + item.getWeight() > player.getMaxCarryWeight()) {
            return new StatusResponse(Status.ExceedMaxCarryWeight);
        }

        item.setOwnerType(ownerTypePlayer);
        item.setOwnerId(player.getId());

        return new StatusResponse(Status.Success);
    }

    /**
     * 丢掉物品
     * @param id 物品的id
     * @return 丢掉的结果
     */
    public StatusResponse dropItem(int id) {
        PlayerEntity player = playerStore.getPlayerEntity();

        ItemEntity item = itemStore.getItem(id);

        if(item.getOwnerType().equals(ownerTypeRoom)||item.getOwnerId() != player.getId()) {
            return new StatusResponse(Status.NotCarry);
        }


        item.setOwnerType(ownerTypeRoom);
        item.setOwnerId(player.getCurrentRoomId());


        return new StatusResponse(Status.Success);

    }

    /**
     * 得到玩家携带的物品
     * @return 玩家携带的物品列表
     */
    public List<ItemEntity> getItems() {
        PlayerEntity player = playerStore.getPlayerEntity();

        return itemStore.getItemsByOwner(ownerTypePlayer, player.getId());
    }

    /**
     * 移动
     * @param direction 移动的方向
     * @return 移动的结果
     */
    public StatusResponse move(String direction) {
        PlayerEntity player = playerStore.getPlayerEntity();

        RoomEntity currentRoom = roomStore.getRoom(player.getCurrentRoomId());

        Integer nextRoomId = currentRoom.getExitRoomIdMap().get(direction);

        if (nextRoomId == null) {
            return new StatusResponse(Status.NoRoom);
        }

        String status = Status.Success;

        if (nextRoomId == 7) {
            nextRoomId = roomStore.getRandomRoomId();
            status = "传送成功";
        }

        pathStore.addPath(player.getCurrentRoomId());

        player.setCurrentRoomId(nextRoomId);

        return new StatusResponse(status);
    }

    /**
     * 回溯
     * @return 回溯的结果
     */
    public StatusResponse back(){
        PlayerEntity player = playerStore.getPlayerEntity();

        if(pathStore.isPathEmpty()){
            return new StatusResponse(Status.NoPath);
        }

        player.setCurrentRoomId(pathStore.drawPath());
        return new StatusResponse(Status.Success);
    }
}
