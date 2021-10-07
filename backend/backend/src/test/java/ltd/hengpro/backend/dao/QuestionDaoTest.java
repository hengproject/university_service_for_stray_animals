package ltd.hengpro.backend.dao;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionDaoTest {
    @Autowired
    QuestionDao questionDao;

    @Test
    void testFindAll(){
        System.out.println(questionDao.findAll());
    }

}