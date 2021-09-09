package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserIdentity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserIdentityDaoTest {
    @Autowired
    UserIdentityDao userIdentityDao;

    @Test
    void findByUserId() {
        UserIdentity userIdentity = userIdentityDao.findByUserId("1");
        System.out.println(userIdentity);

    }
    @Test
    @Transactional
    void register(){
        UserIdentity userIdentity = userIdentityDao.findByUserId("1");
        userIdentity.setUserId("5");
        UserIdentity userIdentity1 = userIdentityDao.saveAndFlush(userIdentity);
        System.out.println(userIdentity1);
    }
}