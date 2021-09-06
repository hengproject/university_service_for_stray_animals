package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dto.SuperUserInfoDto;
import ltd.hengpro.backend.serivice.SuperUserService;
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
class SuperUserServiceImplTest {
    @Autowired
    SuperUserService superUserService;


    @Test
    void superUserGetUserList() throws Exception {
        List<SuperUserInfoDto> superUserInfoDtos = superUserService.superUserGetUserList(PageRequest.of(0, 2));
        System.out.println(superUserInfoDtos);

    }
}