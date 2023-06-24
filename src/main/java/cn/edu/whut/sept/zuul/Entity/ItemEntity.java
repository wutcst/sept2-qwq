package cn.edu.whut.sept.zuul.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *设计物品类
 */
@Data
@AllArgsConstructor
public class ItemEntity {
    private int id;

    private String description;

    private double weight;

    private String ownerType;

    private int ownerId;
}
