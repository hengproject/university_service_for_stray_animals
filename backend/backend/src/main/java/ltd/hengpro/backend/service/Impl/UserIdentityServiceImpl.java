package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.UserIdentityDao;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.service.UserIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

    @Autowired
    UserIdentityDao userIdentityDao;

    public UserIdentity register(UserIdentity userIdentity){
        return userIdentityDao.saveAndFlush(userIdentity);
    }

    public void deleteUserIdentityByUserId(String userId){
        try {
            userIdentityDao.deleteById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new UserAuthException(ExceptionEnum.DELETE_INFO_DAO_NOT_FOUND);
        }
    }
    public void editUserIdentity(EditUserForm editUserForm){
        UserIdentity byUserId = userIdentityDao.findByUserId(editUserForm.getUserId());
        if(ObjectUtils.isEmpty(byUserId)) throw new UserAuthException(ExceptionEnum.EDIT_USER_NOT_FOUND);
        Integer userGroupCode = UserGroupEnum.valueOf(editUserForm.getUserGroup()).getCode();
        byUserId.setUserGroup(userGroupCode);
        userIdentityDao.saveAndFlush(byUserId);
    }
}
