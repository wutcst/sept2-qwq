package cn.edu.whut.sept.zuul.Database;

import cn.edu.whut.sept.zuul.Entity.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ItemStore {
    static int nextId = 1;

    static HashMap<Integer, ItemEntity> itemMap = new HashMap<>();

    /**
     * 根据物品id得到物品的信息
     * @param id 物品id
     * @return 物品的信息
     */
    public ItemEntity getItem(int id) {
        return itemMap.get(id);
    }

    /**
     * 根据物品的所属得到物品的信息
     * @param ownerType 物品属于房间还是玩家
     * @param ownerId 玩家或房间的id
     * @return 物品的信息
     */
    public List<ItemEntity> getItemsByOwner(String ownerType, int ownerId) {
        List<ItemEntity> result = new ArrayList<>();

        for (ItemEntity item : itemMap.values()) {
            if (item.getOwnerType().equals(ownerType) && item.getOwnerId() == ownerId) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * 向库里添加物品
     * @param item 物品实体
     */
    public void addItem(ItemEntity item) {
        if (item.getId() == 0) {
            item.setId(nextId++);
        }

        itemMap.put(item.getId(), item);
    }
}
