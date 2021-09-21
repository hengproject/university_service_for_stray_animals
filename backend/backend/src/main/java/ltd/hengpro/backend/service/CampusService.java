package ltd.hengpro.backend.service;

import ltd.hengpro.backend.entity.Area;
import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.vo.manager.CampusVo;

import java.util.List;

public interface CampusService {
    Campus getCampusById(Integer campusId);
    Campus getCampusByName(String campusName);
    Campus deleteCampusById(Integer campusId);
    Campus addCampus(String campusName);
    List<Campus> getCampusList();
    List<Area> getAreaListByCampusId(Integer campusId);
    Campus editCampus(Campus campus);
    Area addArea(Area area);
    void deleteArea(Integer areaId);
    void editArea(CampusVo campusVo);
}
