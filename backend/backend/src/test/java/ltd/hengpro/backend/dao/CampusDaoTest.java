package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.Campus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CampusDaoTest {
    @Autowired
    CampusDao campusDao;
    @Test
    @Transactional
    public void test01(){
        Optional<Campus> byId = campusDao.findById(1);
        System.out.println(byId.get());
        campusDao.delete(byId.get());

    }

    @Test
    @Transactional
    public void test02(){
        Campus campus = new Campus();
        campus.setCampusId(1);
        campus.setCampusName("123");
        Campus campus1 = campusDao.saveAndFlush(campus);
        System.out.println(campus1);

    }

}