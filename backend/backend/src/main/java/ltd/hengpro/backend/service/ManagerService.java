package ltd.hengpro.backend.service;

import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.vo.manager.CampusVo;

import java.util.List;

public interface ManagerService {
    List<CampusVo> getCampusList();
    List<CampusVo> getAreaAsCampusVoFromCampusId(Integer campusId);
    void editCampus(CampusVo campusVo);
}
