package cn.edu.whut.sept.zuul.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 *该类用于整理响应请求玩家所需要的信息
 */

@Data
@AllArgsConstructor
public class GetPlayerInfoResponse {
    private String name;
    private double CurrentCarryWeight;
    private double maxCarryWeight;
    private int currentRoomId;
    private String currentRoomName;
}
