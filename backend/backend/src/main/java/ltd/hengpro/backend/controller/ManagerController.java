package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.Campus;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.enums.StaffIdentityEnum;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.service.ManagerService;
import ltd.hengpro.backend.service.StaffInfoService;
import ltd.hengpro.backend.service.TokenService;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.manager.BasicInfoVo;
import ltd.hengpro.backend.vo.manager.CampusVo;
import ltd.hengpro.backend.vo.normaluser.UserInfoEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        managerService.deleteCampusByCampusId(Integer.valueOf(requestData));
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
}
