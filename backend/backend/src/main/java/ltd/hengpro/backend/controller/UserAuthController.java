package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.AuthDto;
import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.serivice.StaffInfoService;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserAuthController {
    @Autowired
    UserLoginService userLoginService;

    @Autowired
    StaffInfoService staffInfoService;

    @Autowired
    TokenService tokenService;


    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String jsonString = RequestUtil.getRequestData(httpServletRequest);
        UserLoginVo userLoginVo = JSON.parseObject(jsonString, UserLoginVo.class);
        UserDto userDto = userLoginService.login(userLoginVo);
        ResultVo resultVo = new ResultVo();
        if(ObjectUtils.isEmpty(userDto)) resultVo.setCode(403);
        else {
            userDto.setToken(tokenService.getToken(userDto));
            resultVo.setCode(200);
            resultVo.setData(userDto);

        }
        return JSON.toJSONString(resultVo);
    }

    @GetMapping("/menus")
    public String authMenu(HttpServletRequest httpServletRequest) throws Exception {
        String authorization = httpServletRequest.getHeader("Authorization");

        UserDto userDto;
        try {
            userDto = tokenService.getUserDtoFromUUID(authorization);
        }catch (UserAuthException e){
            return JSON.toJSONString(new ResultVo<Object>(401,"认证未通过，请重新登录",null));
        }
        AuthDto authDto = new AuthDto();
        StaffDto staffDto = staffInfoService.getStuffDtoByUserID(userDto.getUserId());
        BeanUtils.copyProperties(userDto,authDto);
        BeanUtils.copyProperties(staffDto,authDto);
        ResultVo<AuthDto> resultVo = new ResultVo<>(200, "success", authDto);
        return JSON.toJSONString(resultVo);
    }
}
