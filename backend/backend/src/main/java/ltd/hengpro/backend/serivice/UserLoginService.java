package ltd.hengpro.backend.serivice;


import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.vo.UserLoginVo;

public interface UserLoginService {
    UserDto login(UserLoginVo userLoginVo) throws Exception;

    //返回UUID
    String register(UserLoginVo userLoginVo);

    boolean containUser(String username);

    void deleteUserLoginById(String userId);

    void editUserLogin(EditUserForm editUserForm);
}
