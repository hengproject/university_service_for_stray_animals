package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.AreaDao;
import ltd.hengpro.backend.dao.CampusDao;
import ltd.hengpro.backend.dao.CatInfoDao;
import ltd.hengpro.backend.dto.CatAbbrDto;
import ltd.hengpro.backend.dto.CatDto;
import ltd.hengpro.backend.entity.Area;
import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.entity.CatInfo;
import ltd.hengpro.backend.entity.FileRecord;
import ltd.hengpro.backend.form.manager.EditCatForm;
import ltd.hengpro.backend.service.CatService;
import ltd.hengpro.backend.service.IOService;
import ltd.hengpro.backend.service.WebSiteStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    CatInfoDao catInfoDao;

    @Autowired
    CampusDao campusDao;

    @Autowired
    AreaDao areaDao;

    @Autowired
    IOService ioService;

    @Autowired
    WebSiteStatisticsService webSiteStatisticsService;

    @Override
    public List<CatDto> findByPage(Pageable pageable) {
        List<CatInfo> catInfoList = catInfoDao.findAll(pageable).getContent();
        LinkedList<CatDto> catDtos = new LinkedList<>();
        for(CatInfo catInfo : catInfoList){
            CatDto catDto = new CatDto();
            BeanUtils.copyProperties(catInfo,catDto);
            String campusName = campusDao.findById(catInfo.getCampusId()).get().getCampusName();
            catDto.setCampusName(campusName);
            String areaName = areaDao.findById(catInfo.getAreaId()).get().getAreaName();
            catDto.setAreaName(areaName);
            catDtos.add(catDto);
        }

        return catDtos;
    }

    @Override
    public boolean saveCatPhoto(MultipartHttpServletRequest multipartHttpServletRequest,String catId) throws IOException {
        MultipartFile file = multipartHttpServletRequest.getFile("file");
//        System.out.println("上传的文件名称:" + file.getOriginalFilename());
//        System.out.println("上传的文件大小:" + file.getSize());
//        // 接收其他表单参数
//        String name = multipartHttpServletRequest.getParameter("name");
//        String content = multipartHttpServletRequest.getParameter("content");
//        System.out.println("name:" + name);
//        System.out.println("content:" + content);
        System.out.println(catId);
        CatInfo catInfo = catInfoDao.findById(catId).get();
        if (!ObjectUtils.isEmpty(catInfo.getCatPhotoId())) {
            ioService.deleteFile(catInfo.getCatPhotoId());
        }
        FileRecord fileRecord = ioService.storeAFile2FileRecord(file.getInputStream(), file.getOriginalFilename());
        catInfo.setCatPhotoId(fileRecord.getFileId());
        catInfo.setUpdateTime(new Date());
        catInfoDao.save(catInfo);

        return true;

//            String store = ioService.store(file.getInputStream(), file.getOriginalFilename());
//            System.out.println(store);
    }


    public void modifyCatInfo(EditCatForm editCatForm){
        CatInfo catInfo = catInfoDao.findById(editCatForm.getCatId()).get();
        BeanUtils.copyProperties(editCatForm,catInfo);
        catInfo.setUpdateTime(new Date());
        catInfoDao.saveAndFlush(catInfo);
    }

    @Override
    public void addCatInfo(CatInfo catInfo) {
        catInfoDao.saveAndFlush(catInfo);
        webSiteStatisticsService.increaseCatNum();
    }

    @Override
    public void delete(String catId) {
        CatInfo byId = catInfoDao.getById(catId);
        catInfoDao.delete(byId);
        webSiteStatisticsService.decreaseCatNum();

    }

    @Override
    public List<CatAbbrDto> findAllAppearance() {
        List<CatInfo> all = catInfoDao.findAll();
        LinkedList<CatAbbrDto> catAbbrDtos = new LinkedList<>();
        for(CatInfo catInfo : all){
            CatAbbrDto catAbbrDto = new CatAbbrDto();
            Campus campus = campusDao.findById(catInfo.getCampusId()).get();
            Area area = areaDao.findById(catInfo.getAreaId()).get();
            BeanUtils.copyProperties(catInfo, catAbbrDto);
            catAbbrDto.setCampusName(campus.getCampusName());
            catAbbrDto.setAreaName(area.getAreaName());
            catAbbrDtos.add(catAbbrDto);
        }
        return catAbbrDtos;
    }

    @Override
    public void recordFindCat(String catId) {
        CatInfo catInfo = catInfoDao.findById(catId).get();
        catInfo.setLastFindTime(new Date());
        catInfoDao.saveAndFlush(catInfo);
    }
}
