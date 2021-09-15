package ltd.hengpro.backend.controller.util;

import ltd.hengpro.backend.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class FileUploadController {
    @Value("${file.upload.save_path}")
    private String save_path;

    @RequestMapping("/uploadPicture")
    public String uploadFile(HttpServletRequest servletRequest){

        return "666";
    }


}
