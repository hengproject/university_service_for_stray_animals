package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.enums.UserGroupEnum;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.SuperUserService;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.serivice.WebSiteStatisticsService;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.vo.Combine2Vo;
import ltd.hengpro.backend.vo.PageableVo;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.SiteStatisticsVo;
import ltd.hengpro.backend.vo.superuser.UserDtoPOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
public class SuperUserController {
    @Autowired
    SuperUserService superUserService;

    @Autowired
    TokenService tokenService;

    @Autowired
    WebSiteStatisticsService webSiteStatisticsService;

    @PostMapping("/superuser_getUserList")
    public String getUserList(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = authorization(httpServletRequest);
        if(!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        PageableVo pageableVo = JSON.parseObject(requestData, PageableVo.class);
        List<SuperUserInfoDto> superUserInfoDtos = superUserService.superUserGetUserList(PageRequest.of(pageableVo.getPageNum(), pageableVo.getPageSize()));
        SiteStatisticsVo allInfo = webSiteStatisticsService.getAllInfo();

        return JSON.toJSONString(new ResultVo<Object>(200,"success",new Combine2Vo<>(superUserInfoDtos,allInfo)));
    }

    @PostMapping("/superuser_login_or_logout")
    public String LoginOrLogout(HttpServletRequest httpServletRequest) throws IOException {
        String authorization = authorization(httpServletRequest);
        if(!ObjectUtils.isEmpty(authorization)) return authorization;
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        UserDtoPOnline userDtoPOnline = JSON.parseObject(requestData, UserDtoPOnline.class);
        superUserService.loginOrLogout(userDtoPOnline.getUserDto(),userDtoPOnline.getOnline());
        return JSON.toJSONString(new ResultVo<Object>(200,"success",null));
    }

    private String authorization(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");

        UserDto userDto;
        try {
            userDto = tokenService.getUserDtoFromUUID(authorization);
            if(userDto.getUserGroupEnum()!= UserGroupEnum.SUPER_USER)  return JSON.toJSONString(new ResultVo<Object>(403,"身份不合法，拒绝此请求",null));
        }catch (UserAuthException e){
            return JSON.toJSONString(new ResultVo<Object>(401,"认证未通过，请重新登录",null));
        }
        return null;
    }
}
