package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.UserIdentityDao;
import ltd.hengpro.backend.dao.UserLoginDao;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.enums.SpecialIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.service.UserLoginService;
import ltd.hengpro.backend.utils.EnumUtil;
import ltd.hengpro.backend.utils.UUIDUtil;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        UserDto userDto = new UserDto(userLogin.getUserId(), userLogin.getUsername(), EnumUtil.getByCode(userIdentity.getUserGroup(), UserGroupEnum.class), EnumUtil.getByCode(userIdentity.getSpecialIdentity(), SpecialIdentityEnum.class), userIdentity.getStaffId());
        return userDto;
    }

    public String register(UserLoginVo userLoginVo){
        String uuid = UUIDUtil.getUUID();
        UserLogin userLogin = new UserLogin(uuid, userLoginVo.getUsername(), userLoginVo.getPassword());
        userLoginDao.saveAndFlush(userLogin);

        return uuid;
    }

    public boolean containUser(String username){
        UserLogin userLoginByUsername = userLoginDao.findUserLoginByUsername(username);
        return !ObjectUtils.isEmpty(userLoginByUsername);
    }

    public void deleteUserLoginById(String userId){
        try {
            userLoginDao.deleteById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new UserAuthException(ExceptionEnum.DELETE_INFO_DAO_NOT_FOUND);
        }
    }

    @Override
    public void edit(EditUserForm editUserForm){
        UserLogin userLoginByUserId = userLoginDao.findUserLoginByUserId(editUserForm.getUserId());
        if(ObjectUtils.isEmpty(userLoginByUserId)){
            throw new UserAuthException(ExceptionEnum.EDIT_USER_NOT_FOUND);
        }
        UserLogin userLogin = new UserLogin(editUserForm.getUserId(), editUserForm.getUserName(), editUserForm.getPassword());
        userLoginDao.saveAndFlush(userLogin);
    }

    @Override
    public void edit(UserLogin userLogin) {
        userLoginDao.saveAndFlush(userLogin);
    }


    public UserLogin findUserByUserId(String id){
        return userLoginDao.findUserLoginByUserId(id);
    }
}
