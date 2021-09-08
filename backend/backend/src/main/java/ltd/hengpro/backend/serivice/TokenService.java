package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;

public interface TokenService {
    String getToken(UserDto userDto);

    boolean verify(String UUID,UserDto userDto);

    UserDto getUserDtoFromUUID(String uuid);

    boolean online(UserDto userDto);

    boolean online(UserLogin userLogin) throws Exception;
}
