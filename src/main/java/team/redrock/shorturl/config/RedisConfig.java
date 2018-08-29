package team.redrock.shorturl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import team.redrock.shorturl.entity.Url;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Url> messageRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Url> template = new RedisTemplate<Object, Url>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用json的序列化器
        Jackson2JsonRedisSerializer ser = new Jackson2JsonRedisSerializer<Url>(Url.class);
        template.setDefaultSerializer(ser);                 //相当于key的序列化类型和value的序列化类型
        return template;
    }

}
