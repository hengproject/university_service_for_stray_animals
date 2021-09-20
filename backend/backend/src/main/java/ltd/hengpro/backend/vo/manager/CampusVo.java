package ltd.hengpro.backend.vo.manager;

import lombok.Data;
import ltd.hengpro.backend.entity.Campus;

@Data
public class CampusVo {
    private String campusId;
    private String campusName;
    private boolean hasSon;
}
