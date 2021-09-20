package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.service.StaffInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StaffInfoServiceImplTest {

    @Autowired
    StaffInfoService staffInfoService;

    @Test
    void getStuffInfoByUserID() throws Exception {
        StaffDto stuffInfoByUserID = staffInfoService.getStuffDtoByUserID("1");
        System.out.println(stuffInfoByUserID);
    }
}