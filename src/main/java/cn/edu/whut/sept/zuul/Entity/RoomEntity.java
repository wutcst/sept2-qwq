package cn.edu.whut.sept.zuul.Entity;

import cn.edu.whut.sept.zuul.Model.Pair;
import lombok.Data;

import java.util.HashMap;
/**
 *设计房间类
 */
@Data
public class RoomEntity {
    public RoomEntity(int id, String name, String description, Pair location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exitRoomIdMap = new HashMap<>();
        this.location=location;
    }

    private int id;

    private String name;

    private String description;

    private HashMap<String, Integer> exitRoomIdMap;

    private Pair location;
}
