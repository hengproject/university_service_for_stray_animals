package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.StaffDao;
import ltd.hengpro.backend.dao.UserIdentityDao;
import ltd.hengpro.backend.dao.UserLoginDao;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.serivice.StaffInfoService;
import ltd.hengpro.backend.serivice.SuperUserService;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperUserServiceImpl implements SuperUserService {
    @Autowired
    UserLoginDao userLoginDao;

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    StaffInfoService staffInfoService;



    @Override
    public List<SuperUserInfoDto> superUserGetUserList(Pageable pageable) throws Exception {
        List<SuperUserInfoDto> superUserInfoDtoList = new ArrayList<>();

        Page<UserLogin> all = userLoginDao.findAll(pageable);
        List<UserLogin> userLoginList = all.getContent();

        System.out.println(userLoginList.size());
        for(UserLogin userLogin : userLoginList){
            UserDto userDto = userLoginService.login(new UserLoginVo(userLogin));
            StaffDto staffDto = staffInfoService.getStuffDtoByUserID(userLogin.getUserId());
            superUserInfoDtoList.add(new SuperUserInfoDto(staffDto,userDto,userLogin));

        }
        return superUserInfoDtoList;
    }
}
