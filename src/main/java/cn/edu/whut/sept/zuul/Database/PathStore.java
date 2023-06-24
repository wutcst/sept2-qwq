package cn.edu.whut.sept.zuul.Database;

import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public class PathStore {

    private Stack<Integer> path = new Stack<>();

    /**
     * 将最后经过的房间添加进路径
     * @param roomId  最后经过的房间的id
     */
    public void addPath(int roomId){
        path.add(roomId);
    }

    /**
     * 得到最后经过的房间
     * @return 最后经过的房间的id
     */
    public int drawPath(){
        return path.pop();
    }

    /**
     * 判断路径是否为空
     * @return 为空返回true
     */
    public boolean isPathEmpty(){
        return path.isEmpty();
    }
}
