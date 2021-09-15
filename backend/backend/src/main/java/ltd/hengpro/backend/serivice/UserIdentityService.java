package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.entity.UserIdentity;
import ltd.hengpro.backend.form.superuser.EditUserForm;

public interface UserIdentityService {
    UserIdentity register(UserIdentity userIdentity);
    void deleteUserIdentityByUserId(String userId);
    void editUserIdentity(EditUserForm editUserForm);
}
