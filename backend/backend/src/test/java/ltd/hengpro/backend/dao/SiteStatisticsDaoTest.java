package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.SiteStatistics;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SiteStatisticsDaoTest {

    @Autowired
    SiteStatisticsDao siteStatisticsDao;
    @Test
    void findById() {
        Optional<SiteStatistics> byId = siteStatisticsDao.findById(1);
        SiteStatistics siteStatistics = byId.get();
        System.out.println(siteStatistics);
    }
}