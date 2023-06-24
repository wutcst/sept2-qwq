package cn.edu.whut.sept.zuul.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPlayerUserPasswordResponse {
    private String username;
    private String password;
}
