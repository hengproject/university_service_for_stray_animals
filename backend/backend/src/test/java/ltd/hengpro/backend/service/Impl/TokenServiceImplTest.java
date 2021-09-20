package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.enums.SpecialIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenServiceImplTest {

    @Autowired
    TokenService tokenService;

    @Test
    void getToken() {
        UserDto userDto = new UserDto("1", "2", UserGroupEnum.NORMAL_USER, SpecialIdentityEnum.NORMAL_USER,"1");
        String token = tokenService.getToken(userDto);
        System.out.println(token);
        token = tokenService.getToken(userDto);
        System.out.println(token);
        token = tokenService.getToken(userDto);
        System.out.println(token);



        UserDto userDto2 = new UserDto("1", "2", UserGroupEnum.NORMAL_USER, SpecialIdentityEnum.NORMAL_USER,"1");
        token = tokenService.getToken(userDto2);
        System.out.println(token);
        System.out.println(userDto);
        boolean verify = tokenService.verify(token, userDto2);
        System.out.println(verify);
    }

    @Test
    void verify() {
        String uuid="3f1e9e3c0e794786abc69f928dd1fa02";
        UserDto userDto = new UserDto("1", "2", UserGroupEnum.NORMAL_USER, SpecialIdentityEnum.NORMAL_USER,"1");
        System.out.println(userDto);
        boolean verify = tokenService.verify(uuid, userDto);
        System.out.println(verify);
    }

//    @Test
//    void online(){
//        tokenService.online
//    }
}