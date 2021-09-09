package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.UserIdentityDao;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.serivice.UserIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

    @Autowired
    UserIdentityDao userIdentityDao;

    public UserIdentity register(UserIdentity userIdentity){
        return userIdentityDao.saveAndFlush(userIdentity);
    }

}
