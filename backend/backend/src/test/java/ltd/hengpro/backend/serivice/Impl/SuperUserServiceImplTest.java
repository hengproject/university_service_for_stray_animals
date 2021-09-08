package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.converter.UserLogin2UserDto;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.serivice.SuperUserService;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SuperUserServiceImplTest {
    @Autowired
    SuperUserService superUserService;

    @Autowired
    UserLogin2UserDto userLogin2UserDto;

    @Autowired
    TokenService tokenService;


    @Test
    void superUserGetUserList() throws Exception {
        List<SuperUserInfoDto> superUserInfoDtos = superUserService.superUserGetUserList(PageRequest.of(0, 2));
        System.out.println(superUserInfoDtos);

    }

    @Test
    void loginOrLogout() throws Exception {
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUsername("test_superuser");
        userLoginVo.setPassword("123456");
        superUserService.loginOrLogout(userLogin2UserDto.convert(userLoginVo),true);
        List<SuperUserInfoDto> superUserInfoDtos = superUserService.superUserGetUserList(PageRequest.of(0, 2));
        System.out.println(superUserInfoDtos);
    }
}