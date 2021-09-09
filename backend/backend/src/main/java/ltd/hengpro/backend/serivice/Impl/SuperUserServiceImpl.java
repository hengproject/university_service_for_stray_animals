package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.UserLoginDao;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.form.superuser.AddUserForm;
import ltd.hengpro.backend.serivice.StaffInfoService;
import ltd.hengpro.backend.serivice.SuperUserService;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.serivice.WebSiteStatisticsService;
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

    @Autowired
    TokenServiceImpl tokenService;




    @Override
    public List<SuperUserInfoDto> superUserGetUserList(Pageable pageable) throws Exception {
        List<SuperUserInfoDto> superUserInfoDtoList = new ArrayList<>();

        Page<UserLogin> all = userLoginDao.findAll(pageable);
        return getSuperUserInfoDtos(superUserInfoDtoList, all);
    }




    @Override
    public List<SuperUserInfoDto> superUserGetUserListLike(Pageable pageable,String usernameLike) throws Exception {
        List<SuperUserInfoDto> superUserInfoDtoList = new ArrayList<>();

        Page<UserLogin> userLoginByUsernameLike = userLoginDao.findUserLoginByUsernameLike(usernameLike, pageable);
        return getSuperUserInfoDtos(superUserInfoDtoList, userLoginByUsernameLike);
    }

    @Override
    public Long countSuperUserGetUserListLike(String usernameLike) {
        return userLoginDao.countByUsernameLike(usernameLike);
    }


    public void loginOrLogout(UserDto userDto,boolean login){
        if (login){
            tokenService.login(userDto);
        }else {
            tokenService.logout(userDto);
        }
    }

    public boolean containsUser(String username){
        return userLoginService.containUser(username);
    }

    private List<SuperUserInfoDto> getSuperUserInfoDtos(List<SuperUserInfoDto> superUserInfoDtoList, Page<UserLogin> all) throws Exception {
        List<UserLogin> userLoginList = all.getContent();

        for(UserLogin userLogin : userLoginList){
            UserDto userDto = userLoginService.login(new UserLoginVo(userLogin));
            StaffDto staffDto = staffInfoService.getStuffDtoByUserID(userLogin.getUserId());
            boolean online = tokenService.online(userLogin);
            superUserInfoDtoList.add(new SuperUserInfoDto(staffDto,userDto,userLogin,online));

        }
        return superUserInfoDtoList;
    }

    public void registerUser(AddUserForm addUserForm){
        UserLoginVo userLoginVo = new UserLoginVo(addUserForm.getUsername(), addUserForm.getPassword());
        String userId = userLoginService.register(userLoginVo);
    }

}
