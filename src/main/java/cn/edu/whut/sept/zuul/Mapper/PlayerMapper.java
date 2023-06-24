package cn.edu.whut.sept.zuul.Mapper;

import cn.edu.whut.sept.zuul.Entity.Player;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlayerMapper extends BaseMapper<Player> {
    int login(@Param("username") String username,@Param("password") String password);
    Player findUserByUsername(@Param("username")String username);
    Player findUserByID(@Param("ID")int ID);
}
