package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserLoginServiceImplTest {
    @Autowired
    UserLoginService userLoginService;

    @Test
    void login() throws Exception {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUsername("ysh0");
        userLoginVo.setPassword("200108");
        UserDto login = userLoginService.login(userLoginVo);
        System.out.println(login);
    }
    @Test
    @Transactional
    void register(){
        UserLoginVo userLoginVo = new UserLoginVo("ysh666", "133133133");
        String register = userLoginService.register(userLoginVo);
        System.out.println(register);
    }

    @Test
    void containsUser(){
        boolean ysh = userLoginService.containUser("ysh");
        System.out.println(ysh);
    }
}