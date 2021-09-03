package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.serivice.TokenService;
import ltd.hengpro.backend.serivice.UserLoginService;
import ltd.hengpro.backend.utils.PostUtil;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    @Autowired
    TokenService tokenService;


    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String jsonString = PostUtil.getPostData(httpServletRequest);
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

}
