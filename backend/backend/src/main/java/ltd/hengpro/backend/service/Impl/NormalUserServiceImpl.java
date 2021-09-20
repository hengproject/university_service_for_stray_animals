package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.service.NormalUserService;
import ltd.hengpro.backend.service.StaffInfoService;
import ltd.hengpro.backend.service.UserLoginService;
import ltd.hengpro.backend.vo.normaluser.UserInfoEditVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class NormalUserServiceImpl implements NormalUserService {
    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    UserLoginService userLoginService;

    @Override
    public UserInfoEditVo getUserInfoEditVoFromUserDto(UserDto userDto) throws Exception {
        StaffInfo staffInfoFromUserId = staffInfoService.getStaffInfoFromUserId(userDto.getUserId());
        if(ObjectUtils.isEmpty(staffInfoFromUserId)) throw new UserAuthException(ExceptionEnum.USERID_TO_STAFF_NOT_FOUND);
        UserInfoEditVo userInfoEditVo = new UserInfoEditVo();
        BeanUtils.copyProperties(staffInfoFromUserId,userInfoEditVo);
        UserLogin userByUserId = userLoginService.findUserByUserId(userDto.getUserId());
        BeanUtils.copyProperties(userByUserId,userInfoEditVo);
        return userInfoEditVo;
    }

    @Override
    public UserInfoEditVo editUserInfo(UserInfoEditVo userInfoEditVo,UserDto userDto){
        StaffInfo toBeEditStaffInfo = staffInfoService.getStaffInfoFromUserId(userDto.getUserId());
        if(ObjectUtils.isEmpty(toBeEditStaffInfo)) throw new UserAuthException(ExceptionEnum.USERID_TO_STAFF_NOT_FOUND);
        BeanUtils.copyProperties(userInfoEditVo,toBeEditStaffInfo);
        UserLogin toBeEditUserLogin = userLoginService.findUserByUserId(userDto.getUserId());
        BeanUtils.copyProperties(userInfoEditVo,toBeEditUserLogin);

        staffInfoService.edit(toBeEditStaffInfo);
        userLoginService.edit(toBeEditUserLogin);


        return null;
    }

}
