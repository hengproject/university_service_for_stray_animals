package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.SiteStatisticsDao;
import ltd.hengpro.backend.entity.SiteStatistics;
import ltd.hengpro.backend.serivice.WebSiteStatisticsService;
import ltd.hengpro.backend.vo.SiteStatisticsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSiteStatisticsServiceImpl implements WebSiteStatisticsService {
    @Autowired
    SiteStatisticsDao siteStatisticsDao;

    @Override
    public Long getUserNum() {
        return siteStatisticsDao.findById(1).get().getUserNum();
    }

    @Override
    public SiteStatisticsVo getCountInfo() {
        SiteStatisticsVo siteStatisticsVo = new SiteStatisticsVo();
        BeanUtils.copyProperties(siteStatisticsDao.findById(1).get(),siteStatisticsVo);
        return siteStatisticsVo;
    }

    @Override
    public void increaseUserNum() {
        SiteStatistics byId = siteStatisticsDao.getById(1);
        long l = byId.getUserNum() + 1;
        byId.setUserNum(l);
        siteStatisticsDao.saveAndFlush(byId);
    }

    @Override
    public void decreaseUserNum() {
        SiteStatistics byId = siteStatisticsDao.getById(1);
        long l = byId.getUserNum() - 1 ;
        byId.setUserNum(l);
        siteStatisticsDao.saveAndFlush(byId);
    }

}
