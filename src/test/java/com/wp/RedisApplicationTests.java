package com.wp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.wp.modules.sys.entity.Role;
import com.wp.modules.sys.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@CachePut
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> template;

    @Test
    public void contextLoads() {
        Role user = new Role();
        user.setId(1L);
        user.setRole("miss zhan");
        template.opsForValue().set(user.getId()+"",user);
        //原本opsForValue()是只能操作字符串的.现在就可以操作对象了
        Role result = (Role) template.opsForValue().get(user.getId()+"");
        System.out.println(result.toString());
    }
}
