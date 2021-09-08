package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.vo.SiteStatisticsVo;

public interface WebSiteStatisticsService {
    Integer getUserNum();
    SiteStatisticsVo getAllInfo();
}
