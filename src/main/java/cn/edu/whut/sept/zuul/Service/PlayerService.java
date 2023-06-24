package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.Player;
import cn.edu.whut.sept.zuul.Entity.Room;
import cn.edu.whut.sept.zuul.Mapper.PlayerMapper;
import cn.edu.whut.sept.zuul.util.PairUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Service
public class PlayerService {
    /*
    @Resource
    private PlayerMapper playerMapper;

     */
    /*
    public Player PlayerLogin(@RequestParam String username,@RequestParam String password){
        Player user = playerMapper.findUserByUsername(username);
        if(user == null){
            return new Player();
        } else if (Objects.equals(user.getPassword(), password)) {
            return user;
        }
        else
            return new Player();
    }

     */
    /*
    public PairUtil PlayerLocation(@RequestParam int ID){
        Player user = playerMapper.findUserByID(ID);
        return user.getCurrentRoomId();
    }
     */

}
