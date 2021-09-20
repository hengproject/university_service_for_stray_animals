package ltd.hengpro.backend.vo.manager;

import lombok.Data;
import ltd.hengpro.backend.entity.Campus;

import java.util.List;

@Data
public class BasicInfoVo {
    private List<CampusVo> campusList;
}
