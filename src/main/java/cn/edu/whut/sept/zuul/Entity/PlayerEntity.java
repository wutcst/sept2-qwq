package cn.edu.whut.sept.zuul.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 *设计玩家类
 */
@Data
@AllArgsConstructor
public class PlayerEntity {
    private int id;

    private String name;

    private String password;

    private double maxCarryWeight;

    private int currentRoomId;
}
