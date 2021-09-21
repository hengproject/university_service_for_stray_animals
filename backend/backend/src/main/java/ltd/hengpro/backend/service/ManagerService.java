package ltd.hengpro.backend.service;

import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.form.manager.AddAreaForm;
import ltd.hengpro.backend.vo.manager.CampusVo;

import java.util.List;

public interface ManagerService {
    List<CampusVo> getCampusList();
    List<CampusVo> getAreaAsCampusVoFromCampusId(Integer campusId);
    void editCampus(CampusVo campusVo);
    void deleteCampusByCampusId(Integer campusId);
    Campus addCampus(String campusName);
    void addArea(AddAreaForm addAreaForm);
    void deleteAreaById(Integer areaId);
    void editArea(CampusVo campusVo);
}
