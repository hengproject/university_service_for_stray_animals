package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserLogin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class UserLoginDaoTest {
    @Autowired
    UserLoginDao userLoginDao;

    @Test
    void findAll() {
        List<UserLogin> all = userLoginDao.findAll();
        System.out.println(all);
    }

    @Test
    void findByUserNameAndPassword(){

        UserLogin byUserNameAndPassword = userLoginDao.findUserLoginByUsernameAndPassword("ysh","123456789");
        System.out.println(byUserNameAndPassword);
    }
}