package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.CatInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class CatInfoDaoTest {
    @Autowired
    CatInfoDao catInfoDao;

    @Test
    void findAllTest(){
        List<CatInfo> all = catInfoDao.findAll();
        System.out.println(all);
    }


}