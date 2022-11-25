package com.zf.springsecurity.config;

import com.zf.springsecurity.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
* @ClassName: RedisConfig
* @Description: 防止Redis存入时乱码
* @Author: ZF
* @date: 2022/11/19 23:26
*/
@Configuration
public class RedisConfig {

        @Bean
        @SuppressWarnings(value = { "unchecked", "rawtypes" })
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
        {
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);

            FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

            // 使用StringRedisSerializer来序列化和反序列化redis的key值
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(serializer);

            // Hash的key也采用StringRedisSerializer的序列化方式
            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(serializer);

            template.afterPropertiesSet();
            return template;
        }
    }