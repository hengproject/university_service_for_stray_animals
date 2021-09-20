package ltd.hengpro.backend.controller;

import com.alibaba.fastjson.JSON;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.exception.UserAuthException;
import ltd.hengpro.backend.service.IOService;
import ltd.hengpro.backend.service.NormalUserService;
import ltd.hengpro.backend.service.TokenService;
import ltd.hengpro.backend.utils.RequestUtil;
import ltd.hengpro.backend.vo.ResultVo;
import ltd.hengpro.backend.vo.normaluser.UserInfoEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
public class NormalUserController {
    @Autowired
    NormalUserService normalUserService;

    @Autowired
    TokenService tokenService;

    @Autowired
    IOService ioService;

    @PostMapping("/normal_user_get_edit_data")
    public String normalUserGetEditData(HttpServletRequest httpServletRequest) {
        UserDto userDto = getUserDto(httpServletRequest);
        if (ObjectUtils.isEmpty(userDto)) {
            return JSON.toJSONString(new ResultVo<Object>(401, "认证未通过，请重新登录", null));
        }
        UserInfoEditVo userInfoEditVoFromUserDto = null;
        try {
            userInfoEditVoFromUserDto = normalUserService.getUserInfoEditVoFromUserDto(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(new ResultVo<>(200, "success", userInfoEditVoFromUserDto));
    }

    @PostMapping("/normal_user_edit_user_data")
    public String normalUserEditUserData(HttpServletRequest httpServletRequest) throws IOException {
        UserDto userDto = getUserDto(httpServletRequest);
        if (ObjectUtils.isEmpty(userDto)) {
            return JSON.toJSONString(new ResultVo<Object>(401, "认证未通过，请重新登录", null));
        }
        String requestData = RequestUtil.getRequestData(httpServletRequest);
        UserInfoEditVo userInfoEditVo = JSON.parseObject(requestData, UserInfoEditVo.class);
        normalUserService.editUserInfo(userInfoEditVo, userDto);


        return JSON.toJSONString(new ResultVo<>(200, "success", null));
    }

    @PostMapping("/normal_user_upload_file")
    public String recieveFile(HttpServletRequest httpServletRequest) throws IOException {
        // 转换为 MultipartHttpServletRequest
        if (httpServletRequest instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
            // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
            MultipartFile file = multipartRequest.getFile("file");
            System.out.println("上传的文件名称:" + file.getOriginalFilename());
            System.out.println("上传的文件大小:" + file.getSize());
            // 接收其他表单参数
            String name = multipartRequest.getParameter("name");
            String content = multipartRequest.getParameter("content");
            System.out.println("name:" + name);
            System.out.println("content:" + content);
            String store = ioService.store(file.getInputStream(), file.getOriginalFilename());
            System.out.println(store);
            return "OK";
        } else {
            return "不是 MultipartHttpServletRequest";
        }
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
