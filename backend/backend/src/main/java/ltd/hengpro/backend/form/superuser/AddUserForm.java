package ltd.hengpro.backend.form.superuser;

import lombok.Data;

@Data
public class AddUserForm {
    private String username;
    private String password;
    private String userGroup;
    private String staffName;
    private String staffIdentity;
}
