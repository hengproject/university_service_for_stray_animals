package ltd.hengpro.backend.serivice.Impl;

import com.sun.istack.NotNull;
import ltd.hengpro.backend.dao.UserIdentityDao;
import ltd.hengpro.backend.dao.UserLoginDao;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.enums.SpecialIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.utils.EnumUtil;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserIdentityDao userIdentityDao;

    @Autowired
    UserLoginDao userLoginDao;

    @Override
    public UserDto login(UserLoginVo userLoginVo) throws Exception {
        UserLogin userLogin = userLoginDao.findUserLoginByUsernameAndPassword(userLoginVo.getUsername(), userLoginVo.getPassword());
        if(ObjectUtils.isEmpty(userLogin)) return null;
        UserIdentity userIdentity = userIdentityDao.findByUserId(userLogin.getUserId());
        UserDto userDto = new UserDto(userLogin.getUserId(), userLogin.getUsername(), EnumUtil.getByCode(userIdentity.getGroup(), UserGroupEnum.class), EnumUtil.getByCode(userIdentity.getSpecialIdentity(), SpecialIdentityEnum.class), userIdentity.getStaffId());
        return userDto;
    }
}
