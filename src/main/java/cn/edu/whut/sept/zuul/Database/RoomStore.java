package cn.edu.whut.sept.zuul.Database;

import cn.edu.whut.sept.zuul.Entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Random;

@Repository
public class RoomStore {
    static private int nextId = 1;

    static private final HashMap<Integer, RoomEntity> roomMap = new HashMap<>();

    static private final Random random = new Random();

    public int getNextId() {
        return nextId++;
    }

    /**
     * 从库里取得房间
     * @param id 房间的id
     * @return 房间的实体
     */
    public RoomEntity getRoom(int id) {
        return roomMap.get(id);
    }

    /**
     * 随机取得一个房间
     * @return 随机取得的房间的id
     */
    public int getRandomRoomId() {
        return random.nextInt(roomMap.size())+1;
    }

    /**
     * 向库里添加房间
     * @param room 房间的实体
     */
    public void addRoom(RoomEntity room) {
        if (room.getId() == 0) {
            room.setId(nextId++);
        }

        roomMap.put(room.getId(), room);
    }
}
