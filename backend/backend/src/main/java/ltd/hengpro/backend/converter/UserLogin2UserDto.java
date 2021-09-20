package ltd.hengpro.backend.converter;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.service.UserLoginService;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogin2UserDto {
    @Autowired
    UserLoginService userLoginService;

    public UserDto convert(UserLoginVo userLoginVo) throws Exception {
        return userLoginService.login(userLoginVo);
    }
    public UserDto convert(UserLogin userLogin) throws Exception {
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(userLogin,userLoginVo);
        return this.convert(userLoginVo);
    }
}
