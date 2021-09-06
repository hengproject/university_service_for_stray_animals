package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.converter.UserLogin2UserDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    UserLogin2UserDto userLogin2UserDto;
   private static Map<UserDto,String> map =new HashMap<>();
   private static Map<String,UserDto> reverseMap =new HashMap<>();

    public String getToken(UserDto userDto) {
        if(map.containsKey(userDto)) return map.get(userDto);
        return this.login(userDto);
    }

    public boolean verify(String uuid1,UserDto userDto){
        String uuid = map.get(userDto);
        return uuid.equals(uuid1);
    }

    public UserDto getUserDtoFromUUID(String uuid){
        UserDto userDto = reverseMap.get(uuid);
        if(ObjectUtils.isEmpty(userDto)) throw new UserAuthException(ExceptionEnum.TOKEN_TO_USERDTO_NOT_FOUND);
        return userDto;
    }

    public boolean online(UserDto userDto){
        return  map.containsKey(userDto);
    }

    public boolean online(UserLogin userLogin) throws Exception {
        return  this.online(userLogin2UserDto.convert(userLogin));
    }

    public String login(UserDto userDto){
        String uuid = UUIDUtil.getUUID();
        map.put(userDto,uuid);
        reverseMap.put(uuid,userDto);
        return uuid;
    }

    public void logout(UserDto userDto){
        String uuid = map.get(userDto);
        map.remove(userDto);
        reverseMap.remove(uuid);

    }


}
