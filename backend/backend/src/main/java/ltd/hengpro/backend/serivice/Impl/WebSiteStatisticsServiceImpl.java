package ltd.hengpro.backend.serivice.Impl;

import ltd.hengpro.backend.dao.SiteStatisticsDao;
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
    public Integer getUserNum() {
        return siteStatisticsDao.findById(1).get().getUserNum();
    }

    @Override
    public SiteStatisticsVo getAllInfo() {
        SiteStatisticsVo siteStatisticsVo = new SiteStatisticsVo();
        BeanUtils.copyProperties(siteStatisticsDao.findById(1).get(),siteStatisticsVo);
        return siteStatisticsVo;
    }
}
