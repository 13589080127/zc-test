package com.zcs.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class RedisTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.set("k".getBytes(),"v".getBytes(), Expiration.seconds(10000), RedisStringCommands.SetOption.SET_IF_ABSENT);
            }
        });
    }
}
