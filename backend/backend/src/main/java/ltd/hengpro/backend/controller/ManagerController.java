package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.CatInfo;
import ltd.hengpro.backend.entity.Question;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.DaoException;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.form.manager.AddAreaForm;
import ltd.hengpro.backend.form.manager.EditCatForm;
import ltd.hengpro.backend.service.*;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.utils.UUIDUtil;
import ltd.hengpro.backend.vo.PageableVo;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.manager.BasicInfoVo;
import ltd.hengpro.backend.vo.manager.CampusVo;
import ltd.hengpro.backend.vo.normaluser.UserInfoEditVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ManagerController {
    @Autowired
    TokenService tokenService;

    @Autowired
    ManagerService managerService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    CatService catService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/manager_get_basic_info_list")
    public String getBasicInfoList(HttpServletRequest httpServletRequest){
        String authorization = authorization(httpServletRequest);
        if(!ObjectUtils.isEmpty(authorization)) return authorization;
        BasicInfoVo basicInfoVo = new BasicInfoVo();

        List<CampusVo> campusList = managerService.getCampusList();
        basicInfoVo.setCampusList(campusList);
        return JSON.toJSONString(new ResultVo<>(200, "success", basicInfoVo));

    }
    @PostMapping("/manager_edit_campus_information")
    public String modifyCampus(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if(!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        CampusVo campusVo = JSON.parseObject(requestData, CampusVo.class);
        managerService.editCampus(campusVo);
        return JSON.toJSONString(new ResultVo<>(200, "success", null));
    }
    @PostMapping("/manager_delete_campus_information")
    public String deleteCampus(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        try{
            managerService.deleteCampusByCampusId(Integer.valueOf(requestData));
        }catch (DaoException daoException){
            return JSON.toJSONString(new ResultVo<>(406,daoException.getMessage(),null));
        }
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }
    @PostMapping("/manager_add_campus_information")
    public String addCampus(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        managerService.addCampus(requestData);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }
    @PostMapping("/manager_get_basic_info_list_lazy_get_area")
    public String getArea(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if(!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        List<CampusVo> areaAsCampusVoFromCampusId = managerService.getAreaAsCampusVoFromCampusId(Integer.parseInt(requestData));
        return JSON.toJSONString(new ResultVo<>(200,"success",areaAsCampusVoFromCampusId));
    }
    @PostMapping("/manager_add_area")
    public String addArea(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        AddAreaForm addAreaForm = JSON.parseObject(requestData, AddAreaForm.class);
        managerService.addArea(addAreaForm);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }
    @PostMapping("/manager_delete_area")
    public String deleteArea(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);

        try{
            managerService.deleteAreaById(Integer.valueOf(requestData));
        }catch (DaoException daoException){
            return JSON.toJSONString(new ResultVo<>(406,daoException.getMessage(),null));
        }
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }

    @PostMapping("/manager_edit_area")
    public String editArea(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        CampusVo campusVo = JSON.parseObject(requestData, CampusVo.class);
        managerService.editArea(campusVo);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }


    @PostMapping("/manager_upload_file")
    public String recieveFile(HttpServletRequest httpServletRequest, @Param("catId") String catId) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        // 转换为 MultipartHttpServletRequest

        if (httpServletRequest instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
            catService.saveCatPhoto(multipartRequest,catId);
            return JSON.toJSONString(new ResultVo<>(200,"success",null));
        } else {
            return JSON.toJSONString(new ResultVo<Object>(401,"传输错误",null));
        }
    }
    @PostMapping("/manager_modify_cat_info")
    public String modifyCatInfo(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        EditCatForm editCatForm = JSON.parseObject(requestData, EditCatForm.class);
        catService.modifyCatInfo(editCatForm);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }

    @PostMapping("/manager_add_cat_info")
    public String addCatInfo(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        EditCatForm editCatForm = JSON.parseObject(requestData, EditCatForm.class);
        CatInfo catInfo = new CatInfo();
        editCatForm.setCatId(UUIDUtil.getUUID());
        BeanUtils.copyProperties(editCatForm,catInfo);
        catInfo.setCatPhotoId("0");
        catInfo.setUpdateTime(new Date());
        catInfo.setCreateTime(new Date());
        catInfo.setLastFindTime(new Date());
        catService.addCatInfo(catInfo);
        System.out.println(catInfo);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }
    @PostMapping("/manager_delete_cat_info")
    public String deleteCatInfo(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String catId = RequestUtil.getRequestData(httpServletRequest);
        catService.delete(catId);
        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }

    @PostMapping("/manager_get_question_unresolved_info")
    public String getQuestionUnResolved(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        PageableVo pageableVo = JSON.parseObject(requestData, PageableVo.class);
        List<Question> unsolvedQuestion = questionService.getUnsolvedQuestion(PageRequest.of(pageableVo.getPageNum(), pageableVo.getPageSize()));

        return JSON.toJSONString(new ResultVo<>(200,"success",unsolvedQuestion));
    }
    @PostMapping("/manager_set_question_seen")
    public String setQuestionSeen(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = authorization(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String questionId = RequestUtil.getRequestData(httpServletRequest);
        questionService.setQuestionSeen(questionId);

        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }
    @PostMapping("/manager_set_question_resolved")
    public String setQuestionResolved(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = authorization(httpServletRequest);
        UserDto userDto = getUserDto(httpServletRequest);
        if (!ObjectUtils.isEmpty(authorization)) return authorization;
        String questionId = RequestUtil.getRequestData(httpServletRequest);
        questionService.setQuestionSolved(userDto,questionId);

        return JSON.toJSONString(new ResultVo<>(200,"success",null));
    }


    private String authorization(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");

        UserDto userDto;
        try {
            userDto = tokenService.getUserDtoFromUUID(authorization);
            StaffInfo staffInfo = staffInfoService.getStaffInfoFromUserId(userDto.getUserId());
            if(userDto.getUserGroupEnum()!= UserGroupEnum.SUPER_USER||staffInfo.getStaffIdentity()!= StaffIdentityEnum.MANAGER.getCode())  return JSON.toJSONString(new ResultVo<Object>(403,"身份不合法，拒绝此请求",null));
        }catch (UserAuthException e){
            return JSON.toJSONString(new ResultVo<Object>(401,"认证未通过，请重新登录",null));
        }
        return null;
    }

    private UserDto getUserDto(HttpServletRequest httpServletRequest){

        String authorization = httpServletRequest.getHeader("Authorization");
        UserDto userDto;
        try {
            userDto = tokenService.getUserDtoFromUUID(authorization);
        }catch (UserAuthException e){
            return null;
        }

        return userDto;
    }

}
