package ltd.hengpro.backend.serivice;


import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.vo.UserLoginVo;

public interface UserLoginService {
    UserDto login(UserLoginVo userLoginVo) throws Exception;
}
