package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserLogin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Test
    void findAll2(){
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<UserLogin> all = userLoginDao.findAll(pageRequest);
        System.out.println(all.getContent());
    }
}