package ltd.hengpro.backend.serivice;

import ltd.hengpro.backend.vo.SiteStatisticsVo;

public interface WebSiteStatisticsService {
    Long getUserNum();
    SiteStatisticsVo getCountInfo();
    void increaseUserNum();
    void decreaseUserNum();
}
