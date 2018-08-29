package team.redrock.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@ServletComponentScan(basePackages="team.redrock.shorturl")
public class ShorturlApplication {

    public static void main(String[] args)
    {

        SpringApplication.run(ShorturlApplication.class, args);
    }


}
