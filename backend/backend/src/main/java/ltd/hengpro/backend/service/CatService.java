package ltd.hengpro.backend.service;

import ltd.hengpro.backend.dto.CatDto;
import ltd.hengpro.backend.entity.CatInfo;
import ltd.hengpro.backend.form.manager.EditCatForm;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import java.io.IOException;
import java.util.List;

public interface CatService {
    List<CatDto> findByPage(Pageable pageable);
    boolean saveCatPhoto(MultipartHttpServletRequest multipartHttpServletRequest,String catId) throws IOException;
    void modifyCatInfo(EditCatForm editCatForm);
    void addCatInfo(CatInfo catInfo);
    void delete(String catId);
}
