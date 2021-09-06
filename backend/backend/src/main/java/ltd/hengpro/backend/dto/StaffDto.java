package ltd.hengpro.backend.dto;

import lombok.Data;
import ltd.hengpro.backend.enums.StaffIdentityEnum;

import java.io.Serializable;

@Data
public class StaffDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String staffId;

    /** 工作人员身份 */
    private StaffIdentityEnum staffIdentityEnum;

    private String userId;

    private String staffName;
}
