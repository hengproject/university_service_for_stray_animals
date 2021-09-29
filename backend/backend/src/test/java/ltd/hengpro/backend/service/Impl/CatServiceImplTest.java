package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dto.CatDto;
import ltd.hengpro.backend.entity.CatInfo;
import ltd.hengpro.backend.service.CatService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CatServiceImplTest {

    @Autowired
    CatService catService;

    @Test
    void findByPage() {
        List<CatDto> byPage = catService.findByPage(PageRequest.of(0, 2));
        System.out.println(byPage);
    }
}