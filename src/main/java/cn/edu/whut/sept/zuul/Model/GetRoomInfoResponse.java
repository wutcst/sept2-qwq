package cn.edu.whut.sept.zuul.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
/**
 *该类用于整理响应请求房间所需要的信息
 */
@Data
@AllArgsConstructor
public class GetRoomInfoResponse {
    private String name;

    private String description;

    private HashMap<String, Integer> exitRoomIdMap;
}
