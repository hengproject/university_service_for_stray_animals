package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.StaffInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class StaffInfoDaoTest {
    @Autowired
    StaffInfoDao staffInfoDao;

    @Test
    void findByUserId() {
        StaffInfo byUserId = staffInfoDao.findByUserId("1");
        System.out.println(byUserId);
    }

    @Test
    @Transactional
    void register(){
        StaffInfo staffInfo = new StaffInfo("5", "0", "20", new Date(), new Date(), 1, "", "5", "name");
        System.out.println(staffInfoDao.saveAndFlush(staffInfo));
    }
}