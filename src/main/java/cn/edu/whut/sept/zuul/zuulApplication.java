package cn.edu.whut.sept.zuul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
public class zuulApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApplication=
                new SpringApplicationBuilder()
                        .sources(zuulApplication.class)
                        .build();
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Game game = new Game();
        game.play();

    }
}
