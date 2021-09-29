package ltd.hengpro.backend.service;

import ltd.hengpro.backend.vo.SiteStatisticsVo;

public interface WebSiteStatisticsService {
    Long getUserNum();
    SiteStatisticsVo getCountInfo();
    void increaseUserNum();
    void increaseCatNum();
    void decreaseUserNum();
    Long getCatNum();
    void decreaseCatNum();
}
