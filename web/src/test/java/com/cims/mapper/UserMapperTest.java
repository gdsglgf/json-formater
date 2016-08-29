package com.cims.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@ContextConfiguration({"classpath:dispatcher-servlet.xml"})
public class UserMapperTest {
    @Test
    public void testGetByUid() {
        Assert.assertNull(userMapper.getByUid(1000));
    }
    
    @Autowired
    private UserMapper userMapper;
}
