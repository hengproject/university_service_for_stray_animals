package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.CatInfoDao;
import ltd.hengpro.backend.entity.Area;
import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.DaoException;
import ltd.hengpro.backend.form.manager.AddAreaForm;
import ltd.hengpro.backend.service.CampusService;
import ltd.hengpro.backend.service.CatService;
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

    @Autowired
    CatInfoDao catInfoDao;

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

    public void addArea(AddAreaForm addAreaForm){
        Area area = new Area();
        area.setAreaName(addAreaForm.getAreaName());
        area.setCampusId(Integer.valueOf(addAreaForm.getCampusId()));
        campusService.addArea(area);
    }

    public void deleteAreaById(Integer areaId){
        long count = catInfoDao.countByAreaId(areaId);
        if(count!=0) throw new DaoException(ExceptionEnum.AREA_OR_CAMPUS_DELETE_REFUSE);
        campusService.deleteArea(areaId);
    }

    @Override
    public void editArea(CampusVo campusVo) {

        campusService.editArea(campusVo);
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

        public void editCampus(CampusVo campusVo){
            Campus campus = new Campus();
            campus.setCampusId(Integer.valueOf(campusVo.getCampusId()));
            campus.setCampusName(campusVo.getCampusName());
            campusService.editCampus(campus);
        }

        public void deleteCampusByCampusId(Integer campusId){
            long count = catInfoDao.countByCampusId(campusId);
            if(count!=0) throw new DaoException(ExceptionEnum.AREA_OR_CAMPUS_DELETE_REFUSE);
            campusService.deleteCampusById(campusId);
        }
}
