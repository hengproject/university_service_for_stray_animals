package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.StaffInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StaffDaoTest {
    @Autowired
    StaffDao staffDao;

    @Test
    void findByUserId() {
        StaffInfo byUserId = staffDao.findByUserId("1");
        System.out.println(byUserId);
    }
}