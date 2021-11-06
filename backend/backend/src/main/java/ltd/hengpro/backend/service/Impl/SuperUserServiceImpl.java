package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.UserLoginDao;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.form.superuser.AddUserForm;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.service.*;
import ltd.hengpro.backend.utils.UUIDUtil;
import ltd.hengpro.backend.vo.UserLoginVo;
import ltd.hengpro.backend.vo.superuser.RowInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SuperUserServiceImpl implements SuperUserService {
    @Autowired
    UserLoginDao userLoginDao;

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    UserIdentityService userIdentityService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    WebSiteStatisticsService webSiteStatisticsService;


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
        String staffId=UUIDUtil.getUUID();
        StaffInfo staffInfo = new StaffInfo(staffId, "0", "20", new Date(), new Date(), StaffIdentityEnum.valueOf(addUserForm.getStaffIdentity()).getCode(), "", userId, addUserForm.getStaffName());
        staffInfoService.register(staffInfo);
        UserIdentity userIdentity = new UserIdentity(userId, UserGroupEnum.valueOf(addUserForm.getUserGroup()).getCode(), 0, staffId);
        userIdentityService.register(userIdentity);
        webSiteStatisticsService.increaseUserNum();
    }

    public void deleteUser(RowInfoVo rowInfoVo){
        String userId = rowInfoVo.getUserLogin().getUserId();
        String staffId = rowInfoVo.getStaffDto().getStaffId();
        try{
        userLoginService.deleteUserLoginById(userId);
        }catch (UserAuthException userAuthException){
            userAuthException.printStackTrace();
        }
        try {
            userIdentityService.deleteUserIdentityByUserId(userId);
        }catch (UserAuthException userAuthException){
            userAuthException.printStackTrace();
        }
        try {
            staffInfoService.delete(staffId);
        }catch (UserAuthException userAuthException){
            userAuthException.printStackTrace();
        }
        webSiteStatisticsService.decreaseUserNum();
    }

    public void modifyUser(EditUserForm editUserForm){
        try {
            userLoginService.edit(editUserForm);
        }catch (UserAuthException userAuthException){
            userAuthException.printStackTrace();
        }
        try{
            userIdentityService.editUserIdentity(editUserForm);
        }catch (UserAuthException userAuthException) {
            userAuthException.printStackTrace();
        }
        try{
            staffInfoService.edit(editUserForm);
        }catch (UserAuthException userAuthException) {
            userAuthException.printStackTrace();
        }

    }
}
