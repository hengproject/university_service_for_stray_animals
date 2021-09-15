package ltd.hengpro.backend.form.superuser;

import lombok.Data;

@Data
public class AddUserForm {
    private String username;
    private String password;
    private String userGroup;
    private String staffName;
    private String staffIdentity;

    public boolean validate(){
        return this.getPassword() != null && this.getUsername() != null && this.getUserGroup() != null && this.getStaffIdentity() != null && this.getStaffName() != null;
    }
}
