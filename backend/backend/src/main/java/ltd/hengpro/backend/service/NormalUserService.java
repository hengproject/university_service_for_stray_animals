package ltd.hengpro.backend.service;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.vo.normaluser.UserInfoEditVo;

public interface NormalUserService {
    public UserInfoEditVo getUserInfoEditVoFromUserDto(UserDto userDto) throws Exception;
    UserInfoEditVo editUserInfo(UserInfoEditVo userInfoEditVo,UserDto userDto);
}
