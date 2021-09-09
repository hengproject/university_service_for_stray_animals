package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.serivice.UserIdentityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserIdentityServiceImplTest {
    @Autowired
    UserIdentityService userIdentityService;

    @Test
    @Transactional
    void register() {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId("5");
        System.out.println(userIdentityService.register(userIdentity));
    }
}