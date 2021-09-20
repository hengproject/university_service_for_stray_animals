package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.Area;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AreaDaoTest {
    @Autowired
    AreaDao areaDao;

    @Test
    @Transactional
    public void Test01(){
        Optional<Area> byId = areaDao.findById(0);
        List<Area> byCampusId = areaDao.findByCampusId(0);
        System.out.println(byCampusId);
    }

}