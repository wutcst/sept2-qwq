package cn.edu.whut.sept.zuul.Database;

import cn.edu.whut.sept.zuul.Entity.PlayerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerStore {
    static double defaultMaxCarryWeight = 100;

    static PlayerEntity playerEntity = new PlayerEntity(1, "yjr", "123456",defaultMaxCarryWeight, 1);

    /**
     * 得到玩家的实体
     * @return 玩家的实体
     */
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
}
