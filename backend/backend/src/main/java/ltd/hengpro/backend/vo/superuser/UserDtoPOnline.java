package ltd.hengpro.backend.vo.superuser;

import lombok.Data;
import ltd.hengpro.backend.dto.UserDto;

@Data
public class UserDtoPOnline {
    UserDto userDto;
    Boolean online;

    @Data
    public static class PageableVo {
        private Integer pageNum;
        private Integer pageSize;
        private String query;
    }
}
