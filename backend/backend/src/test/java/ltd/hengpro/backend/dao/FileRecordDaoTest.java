package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.CatInfo;
import ltd.hengpro.backend.entity.FileRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileRecordDaoTest {
    @Autowired
    FileRecordDao fileRecordDao;


    @Test
    void findAllTest(){
        List<FileRecord> all = fileRecordDao.findAll();
        System.out.println(all);
    }

}