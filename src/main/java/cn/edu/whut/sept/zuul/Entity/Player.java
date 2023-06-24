package cn.edu.whut.sept.zuul.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Player")
public class Player {
    @TableId(type = IdType.AUTO)
    private int user_id;
    private int limitWeight; //最大承载量
    private int currentWeight;//现有负载量
    private String name; //姓名

    private String password;//用户密码
    private int currentRoomId; //当前所处房间id
    private ArrayList<Things> ownThings; //背包物品

    public Player(int limitWeight, String name, int currentRoomId) {
        this.limitWeight = limitWeight;
        this.name = name;
        this.currentRoomId = currentRoomId;
        ownThings = new ArrayList<>();
        currentWeight = 0;
    }

    /**
     * 增加负重
     * @param x 增加负重量
     */
    public void updateLimitWeight(int x){this.limitWeight += x;}


    /**
     * 显示玩家所拥有的的物品项目
     */
    public void showThings() {
        System.out.print("你拥有以下物品:");
        int sumWeight = 0;
        for(Things thing : ownThings) {
            System.out.print(thing.getName() + " ");
            sumWeight += thing.getWeight();
        }
        System.out.println();
        System.out.println("总重量是: "+sumWeight);
    }

    /**
     * 拿取物品
     * @param thing 物品
     * @return 返回bool变量，固定为1
     */
    public boolean takeThings(Things thing) {
        if(currentWeight + thing.getWeight() > limitWeight) return false;
        currentWeight += thing.getWeight();
        ownThings.add(thing);
        return true;
    }

    /**
     * 放下物品
     * @param thingsName 物品名称String
     * @return 返回丢弃的物品
     */
    public Things dropThings(String thingsName) {
        int pos = -1;
        for(int i = 0; i < ownThings.size(); i++) {
            Things thing = ownThings.get(i);
            if(thing.getName().equals(thingsName)) {
                pos = i;
                break;
            }
        }
        if(pos == -1) return null;
        Things thing = ownThings.get(pos);
        this.currentWeight-=thing.getWeight();
        ownThings.remove(pos);
        return thing;
    }

    /**
     * 吃魔法❥饼干
     */
    public void eatCookie(){
        boolean haveCookie=false;
        for (Things things:ownThings){
            if(things.getName().equals("cookie")){
                haveCookie =true;
                ownThings.remove(things);
                System.out.println("你吃了一块魔法饼干，你的负重能力增加啦,你可以多拿10公斤东西");
                this.limitWeight+=10;
                this.currentWeight-=5;
                System.out.println("现在你的负重能力为"+this.limitWeight+"kg");
                break;
            }
        }
        if (!haveCookie)
            System.out.println("你没有饼干");
    }
}
