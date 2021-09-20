package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.service.UserIdentityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserIdentityServiceImplTest {
    @Autowired
    UserIdentityService userIdentityService;

    @Test
    @Transactional
    void register() {
        UserIdentity userIdentity = new UserIdentity("5",1,1,"5");
        userIdentity.setUserId("5");
        System.out.println(userIdentityService.register(userIdentity));
    }
}