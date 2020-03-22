package com.back.odor;

import com.back.odor.menu.system.usermgmt.controller.UserMgmtController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = UserMgmtController.class,
        properties = {
                "user.email=johncrist2000@gmail.com",
                "user.nickname=Julius"
        }
)
public class PropertiesTest {

    @Autowired
    Environment environment;

    @Test
    public void testMethod() {
        System.out.println("아뒤: " + environment.getProperty("user.userId"));
        System.out.println("나이: " + environment.getProperty("user.age"));
        System.out.println("이멜: " + environment.getProperty("user.email"));
        System.out.println("닉넴: " + environment.getProperty("user.nickname"));
    }
}
