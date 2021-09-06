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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserLoginServiceImplTest {
    @Autowired
    UserLoginService userLoginService;

    @Test
    void login() throws Exception {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUsername("ysh");
        userLoginVo.setPassword("123456789");
        UserDto login = userLoginService.login(userLoginVo);
        System.out.println(login);
    }
}