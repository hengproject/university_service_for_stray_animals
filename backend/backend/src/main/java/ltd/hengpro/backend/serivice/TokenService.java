package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.dto.UserDto;

public interface TokenService {
    String getToken(UserDto userDto);

    boolean verify(String UUID,UserDto userDto);
}
