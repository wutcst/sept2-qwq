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
    /**
     * 生成房间和房间里的物品
     * @param roomStore 存储房间
     * @param itemStore 存储物品
     */
    public RoomService(RoomStore roomStore, ItemStore itemStore) {
        this.roomStore = roomStore;
        this.itemStore = itemStore;

        RoomEntity outside = new RoomEntity(roomStore.getNextId(), "outside", "你在大学的入口处",new Pair(0,0));
        RoomEntity theater = new RoomEntity(roomStore.getNextId(), "theater", "你在电影院中",new Pair(1,0));
        RoomEntity pub = new RoomEntity(roomStore.getNextId(), "pub", "你在大学酒吧中",new Pair(-1,0));
        RoomEntity lab = new RoomEntity(roomStore.getNextId(), "lab", "你在实验室中",new Pair(0,-1));
        RoomEntity office = new RoomEntity(roomStore.getNextId(), "office", "你在计算机办公室中，找肖建波聊聊吧",new Pair(1,-1));
        RoomEntity library = new RoomEntity(roomStore.getNextId(), "library", "in the school's library",new Pair(-1,1));
        RoomEntity magic = new RoomEntity(roomStore.getNextId(), "magic", "一个魔法房间，你可以从这里传送到任意你想去的位置",new Pair(1,1));

        outside.getExitRoomIdMap().put("east", theater.getId());
        outside.getExitRoomIdMap().put("south", lab.getId());
        outside.getExitRoomIdMap().put("west", pub.getId());
        outside.getExitRoomIdMap().put("north", 0);

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

        itemStore.addItem(new ItemEntity(0, "apple", 10, ownerTypeRoom, outside.getId()));
        itemStore.addItem(new ItemEntity(0, "DarkMatter", 65535, ownerTypeRoom, outside.getId()));
        itemStore.addItem(new ItemEntity(0, "beer", 20, ownerTypeRoom, pub.getId()));
        itemStore.addItem(new ItemEntity(0, "xjb", 200, ownerTypeRoom, office.getId()));
        itemStore.addItem(new ItemEntity(0, "book", 20, ownerTypeRoom, library.getId()));
        itemStore.addItem(new ItemEntity(0, "cookie", 5, ownerTypeRoom, lab.getId()));
        itemStore.addItem(new ItemEntity(0, "computer", 80, ownerTypeRoom, lab.getId()));
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

}
