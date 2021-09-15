package ltd.hengpro.backend.form.superuser;

import lombok.Data;

@Data
public class EditUserForm {
    private String userId;
    private String userName;
    private String password;
    private String userGroup;
    private String staffId;
    private String staffName;
    private String staffIdentity;
}
