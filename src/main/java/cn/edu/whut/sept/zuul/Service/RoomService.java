package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.ItemEntity;
import cn.edu.whut.sept.zuul.Entity.RoomEntity;
import cn.edu.whut.sept.zuul.Database.ItemStore;
import cn.edu.whut.sept.zuul.Model.GetRoomInfoResponse;
import cn.edu.whut.sept.zuul.Model.Pair;
import cn.edu.whut.sept.zuul.Database.RoomStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private static final String ownerTypeRoom = "Room";

    private final RoomStore roomStore;

    private final ItemStore itemStore;

    private final List<RoomEntity> roomList;
    /**
     * 生成房间和房间里的物品
     * @param roomStore 存储房间
     * @param itemStore 存储物品
     */
    public RoomService(RoomStore roomStore, ItemStore itemStore,List<RoomEntity> rooms) {
        this.roomStore = roomStore;
        this.itemStore = itemStore;
        this.roomList = rooms;

        RoomEntity outside = new RoomEntity(roomStore.getNextId(), "outside", "你在大学的入口处",new Pair(0,0));
        RoomEntity theater = new RoomEntity(roomStore.getNextId(), "theater", "你在电影院中",new Pair(1,0));
        RoomEntity pub = new RoomEntity(roomStore.getNextId(), "pub", "你在大学酒吧中",new Pair(-1,0));
        RoomEntity lab = new RoomEntity(roomStore.getNextId(), "lab", "你在实验室中",new Pair(0,-1));
        RoomEntity office = new RoomEntity(roomStore.getNextId(), "office", "你在计算机办公室中，找肖建波聊聊吧",new Pair(1,-1));
        RoomEntity library = new RoomEntity(roomStore.getNextId(), "library", "你在学校图书馆中",new Pair(-1,1));
        RoomEntity magic = new RoomEntity(roomStore.getNextId(), "magic", "一个魔法房间，你可以从这里传送到任意你想去的位置",new Pair(1,1));

        outside.getExitRoomIdMap().put("east", theater.getId());
        outside.getExitRoomIdMap().put("south", lab.getId());
        outside.getExitRoomIdMap().put("west", pub.getId());
        //outside.getExitRoomIdMap().put("north", 0);

        theater.getExitRoomIdMap().put("west", outside.getId());
        theater.getExitRoomIdMap().put("south", office.getId());
        theater.getExitRoomIdMap().put("north", magic.getId());

        pub.getExitRoomIdMap().put("east", outside.getId());
        pub.getExitRoomIdMap().put("north", library.getId());

        lab.getExitRoomIdMap().put("north", outside.getId());
        lab.getExitRoomIdMap().put("east", office.getId());

        office.getExitRoomIdMap().put("west", lab.getId());
        office.getExitRoomIdMap().put("north", theater.getId());

        library.getExitRoomIdMap().put("south", pub.getId());

        magic.getExitRoomIdMap().put("south",theater.getId());

        roomStore.addRoom(outside);
        roomStore.addRoom(theater);
        roomStore.addRoom(pub);
        roomStore.addRoom(lab);
        roomStore.addRoom(office);
        roomStore.addRoom(library);
        roomStore.addRoom(magic);

        itemStore.addItem(new ItemEntity(1, "apple","我是可以被吃掉的苹果", 10, ownerTypeRoom, outside.getId(),1));
        itemStore.addItem(new ItemEntity(2, "DarkMatter","你能拿动这个说明你开挂了", 65535, ownerTypeRoom, outside.getId(),0));
        itemStore.addItem(new ItemEntity(3, "beer", "一杯人畜无害的啤酒",20, ownerTypeRoom, pub.getId(),1));
        itemStore.addItem(new ItemEntity(4, "xjb","是肖建波，快跑口牙！" ,200, ownerTypeRoom, office.getId(),0));
        itemStore.addItem(new ItemEntity(5, "book","是一本书" ,20, ownerTypeRoom, library.getId(),0));
        itemStore.addItem(new ItemEntity(6, "cookie","是魔法饼干，食用后+20最大承载力" ,5, ownerTypeRoom, lab.getId(),1));
        itemStore.addItem(new ItemEntity(7, "computer", "是一个破旧不堪的电脑",80, ownerTypeRoom, lab.getId(),0));

        roomList.add(outside);
        roomList.add(theater);
        roomList.add(pub);
        roomList.add(lab);
        roomList.add(office);
        roomList.add(library);
        roomList.add(magic);
    }

    /**
     * 得到房间的信息
     * @param roomId 房间的id
     * @return 房间的信息
     */
    public GetRoomInfoResponse getRoomInfo(int roomId) {
        RoomEntity room = roomStore.getRoom(roomId);

        return new GetRoomInfoResponse(
                room.getName(),
                room.getDescription(),
                room.getExitRoomIdMap()
        );
    }

    /**
     * 得到房间里的物品
     * @param roomId 房间的id
     * @return 房间里物品的信息
     */
    public List<ItemEntity> getItemsInRoom(int roomId) {
        return itemStore.getItemsByOwner(ownerTypeRoom, roomId);
    }

    /**
     * 所有房间
     * @return 返回所有房间
     */
    public List<RoomEntity> getAllRooms(){
        return roomList;
    }

}
