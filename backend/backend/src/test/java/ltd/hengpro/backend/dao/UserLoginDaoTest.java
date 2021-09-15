package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
    void findByUserNameAndPassword() {

        UserLogin byUserNameAndPassword = userLoginDao.findUserLoginByUsernameAndPassword("ysh", "123456789");
        System.out.println(byUserNameAndPassword);
    }

    @Test
    void findAll2() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<UserLogin> all = userLoginDao.findAll(pageRequest);
        System.out.println(all.getContent());
    }
    @Test
    void findUserLoginByUsernameLikeTest(){
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<UserLogin> test = userLoginDao.findUserLoginByUsernameLike("%test%", pageRequest);
        System.out.println(test.getContent());
    }
    @Test
    void userNameLikeTest(){
        Long aLong = userLoginDao.countByUsernameLike("%test%");
        System.out.println(aLong);
    }

    @Test
    @Transactional
    void saveTest(){
        String uuid = UUIDUtil.getUUID();
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(uuid);
        userLogin.setUsername("ysh666");
        userLogin.setPassword("133133133");
        UserLogin userLogin1 = userLoginDao.saveAndFlush(userLogin);
        System.out.println(userLogin1);
    }

    @Test
    void findUserLoginByUserName(){
        UserLogin ysh = userLoginDao.findUserLoginByUsername("ysh");
        System.out.println(ysh);
    }

    @Test
    @Transactional
    void deleteUserLoginByUserId(){
        userLoginDao.deleteById("5");
    }
    @Test
    @Transactional
    void editUserLogin(){
        UserLogin yshhghhh = new UserLogin("1", "yshhghhh", "99999");
        UserLogin userLogin = userLoginDao.saveAndFlush(yshhghhh);
        System.out.println(userLogin);

    }
    @Test
    void findUserById(){
        UserLogin userLoginByUserId = userLoginDao.findUserLoginByUserId("5");
        System.out.println(userLoginByUserId);
    }

}