package ltd.hengpro.backend.dto;

import lombok.Data;
import ltd.hengpro.backend.enums.SpecialIdentityEnum;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;

import java.io.Serializable;

@Data
public class AuthDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserGroupEnum userGroupEnum;

    private StaffIdentityEnum staffIdentityEnum;

    private SpecialIdentityEnum specialIdentityEnum;

}
