package ltd.hengpro.backend.vo.superuser;

import lombok.Data;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.UserLogin;


@Data
public class RowInfoVo {
    private boolean online;
    private UserDto userDto;
    private UserLogin userLogin;
    private StaffDto staffDto;
}
