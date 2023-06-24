package cn.edu.whut.sept.zuul.Controller;

import cn.edu.whut.sept.zuul.Entity.Player;
import cn.edu.whut.sept.zuul.Game;
import cn.edu.whut.sept.zuul.Service.PlayerService;
import cn.edu.whut.sept.zuul.util.PairUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private PlayerService playerService;
    /*
    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse rsp){
        Player data = playerService.PlayerLogin(username, password);
        return String.valueOf(data.getUser_id());
    }

     */
    //@GetMapping("/getLocation")
    //public PairUtil getLocation(String userID){
        //return gameService;
    //}

}
