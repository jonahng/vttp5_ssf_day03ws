package com.jonah.vttp5_ssf_day03ws.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.jonah.vttp5_ssf_day03ws.constant.Constant;

public class ValueRepo {
    @Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, String> template;

    public void createValue(String key, String value) {
        template.opsForValue().set(key, value);

        // setIfPresent
        // setIfAbsent
    }

    // slide 25 -retrieve a value
    public String getValue(String key) {
        return template.opsForValue().get(key);
    }

    // slide 27 - delete
    public Boolean deleteValue(String key) {
        return template.delete(key);
    }

    // slide 26 - only works for key with integer value
    public void incrementValue(String key) {
        template.opsForValue().increment(key);
    }

    public void decrementValue(String key) {
        template.opsForValue().decrement(key);
    }

    public void incrementByValue(String key, Integer value) {
        template.opsForValue().increment(key, value);
    }

    public void decrementByValue(String key, Integer value) {
        template.opsForValue().decrement(key, value);
    }

    // slide 28
    public Boolean checkExists(String key) {
        return template.hasKey(key);
    }

}
