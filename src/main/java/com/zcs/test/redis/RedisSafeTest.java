package com.zcs.test.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

public class RedisSafeTest {

    private static RedisTemplate redisTemplate = new RedisTemplate();

    public static void main(String[] args) {
        redisTemplate.setKeySerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        //一条命令 搞定 如果不存在 则进行设置数据，且搞个过期时间
        redisTemplate.execute((RedisCallback) redisConnection ->
                redisConnection.set("k".getBytes(),"v".getBytes(), Expiration.seconds(10000), RedisStringCommands.SetOption.SET_IF_ABSENT));
    }
}
