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
    boolean online;

    public SuperUserInfoDto(StaffDto staffDto, UserDto userDto, UserLogin userLogin, boolean online) {
        this.staffDto = staffDto;
        this.userDto = userDto;
        this.userLogin = userLogin;
        this.online = online;
    }

    public SuperUserInfoDto() {
    }
}
