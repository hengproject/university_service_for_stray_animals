package ltd.hengpro.backend.dto;

import lombok.Data;
import ltd.hengpro.backend.entity.UserLogin;

import java.io.Serializable;

@Data
public class SuperUserInfoDto implements Serializable {


    private static final long serialVersionUID = 1L;
    StaffDto staffDto;
    UserDto userDto;
    UserLogin userLogin;

    public SuperUserInfoDto(StaffDto staffDto, UserDto userDto, UserLogin userLogin) {
        this.staffDto = staffDto;
        this.userDto = userDto;
        this.userLogin = userLogin;
    }

    public SuperUserInfoDto() {
    }
}
