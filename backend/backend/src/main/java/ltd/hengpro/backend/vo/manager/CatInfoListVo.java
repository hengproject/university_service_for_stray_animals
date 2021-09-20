package ltd.hengpro.backend.vo.manager;

import lombok.Data;
import ltd.hengpro.backend.entity.CatInfo;

import java.util.List;

@Data
public class CatInfoListVo {
    private List<CatInfo> catInfoList;
}
