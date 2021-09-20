package ltd.hengpro.backend.service;

import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.form.superuser.AddUserForm;
import ltd.hengpro.backend.form.superuser.EditUserForm;
import ltd.hengpro.backend.vo.superuser.RowInfoVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuperUserService {
    List<SuperUserInfoDto> superUserGetUserList(Pageable pageable) throws Exception;

    void loginOrLogout(UserDto userDto, boolean login);

    List<SuperUserInfoDto> superUserGetUserListLike(Pageable pageable,String usernameLike) throws Exception;

    Long countSuperUserGetUserListLike(String usernameLike);

    boolean containsUser(String username);

    void registerUser(AddUserForm addUserForm);

    void deleteUser(RowInfoVo rowInfoVo);

    void modifyUser(EditUserForm editUserForm);
}
