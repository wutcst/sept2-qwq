package cn.edu.whut.sept.zuul.Model;

import lombok.Data;

@Data
public class Pair {
    int x;
    int y;
    public Pair(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
}
