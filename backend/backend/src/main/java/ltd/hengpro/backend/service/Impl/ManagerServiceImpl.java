package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.entity.Area;
import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.DaoException;
import ltd.hengpro.backend.service.CampusService;
import ltd.hengpro.backend.service.ManagerService;
import ltd.hengpro.backend.vo.manager.CampusVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    CampusService campusService;

    public List<CampusVo> getCampusList(){
        List<CampusVo> campusVoList= new ArrayList<>();
        List<Campus> campusList = campusService.getCampusList();
        CampusVo campusVo;
        for(int i =0;i<campusList.size();i++){
            campusVo=new CampusVo();
            Campus campus = campusList.get(i);
            campusVo.setCampusId(""+campus.getCampusId());
            campusVo.setCampusName(campus.getCampusName());
            campusVo.setHasSon(true);
            campusVoList.add(campusVo);
        }
        return campusVoList;
    }

    public void addArea(Area area){

    }
    public Campus addCampus(String campusName){
        Campus campusByName = campusService.getCampusByName(campusName);
        if(!ObjectUtils.isEmpty(campusByName))throw new DaoException(ExceptionEnum.RE_ADD_EXCEPTION);
        return campusService.addCampus(campusName);
    }
    public List<CampusVo> getAreaAsCampusVoFromCampusId(Integer campusId){
        List<Area> areaListByCampusId = campusService.getAreaListByCampusId(campusId);
        List<CampusVo> campusVoList= new ArrayList<>();
        CampusVo campusVo;
        for (Area value : areaListByCampusId) {
            campusVo = new CampusVo();
            campusVo.setCampusId(campusId + "." + value.getAreaId());
            campusVo.setHasSon(false);
            campusVo.setCampusName(value.getAreaName());
            campusVoList.add(campusVo);
        }
        return campusVoList;
    }


}
