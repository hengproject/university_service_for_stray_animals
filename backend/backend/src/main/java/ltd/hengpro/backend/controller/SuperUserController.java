package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.SuperUserService;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.vo.PageableVo;
import ltd.hengpro.backend.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class SuperUserController {
    @Autowired
    SuperUserService superUserService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/superuser_getUserList")
    public String getUserList(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = httpServletRequest.getHeader("Authorization");

        UserDto userDto;
        try {
            userDto = tokenService.getUserDtoFromUUID(authorization);
            if(userDto.getUserGroupEnum()!= UserGroupEnum.SUPER_USER)  return JSON.toJSONString(new ResultVo<Object>(403,"身份不合法，拒绝此请求",null));
        }catch (UserAuthException e){
            return JSON.toJSONString(new ResultVo<Object>(401,"认证未通过，请重新登录",null));
        }
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        PageableVo pageableVo = JSON.parseObject(requestData, PageableVo.class);
        List<SuperUserInfoDto> superUserInfoDtos = superUserService.superUserGetUserList(PageRequest.of(pageableVo.getPageNum(), pageableVo.getPageSize()));


        return JSON.toJSONString(new ResultVo<Object>(200,"success",superUserInfoDtos));
    }
}
