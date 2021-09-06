package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
   private static Map<UserDto,String> map =new HashMap<>();
   private static Map<String,UserDto> reverseMap =new HashMap<>();

    public String getToken(UserDto userDto) {
        if(map.containsKey(userDto)) return map.get(userDto);
        String uuid = UUIDUtil.getUUID();
        map.put(userDto,uuid);
        reverseMap.put(uuid,userDto);
        return uuid;
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


}
