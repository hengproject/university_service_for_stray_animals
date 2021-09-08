package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.SiteStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteStatisticsDao extends JpaRepository<SiteStatistics,Integer> {
    @Override
    Optional<SiteStatistics> findById(Integer integer);
}
