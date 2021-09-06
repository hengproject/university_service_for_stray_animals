package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dto.StaffDto;
import ltd.hengpro.backend.entity.StaffInfo;
import ltd.hengpro.backend.serivice.StaffInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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