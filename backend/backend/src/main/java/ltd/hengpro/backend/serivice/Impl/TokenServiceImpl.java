package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
   private static Map<UserDto,String> map =new HashMap<>();

    public String getToken(UserDto userDto) {
        if(map.containsKey(userDto)) return map.get(userDto);
        String uuid = UUIDUtil.getUUID();
        map.put(userDto,uuid);
        return uuid;
    }

    public boolean verify(String uuid1,UserDto userDto){
        String uuid = map.get(userDto);
        return uuid.equals(uuid1);
    }

}
