package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.CatInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatInfoDao extends JpaRepository<CatInfo, String> {
    @Override
    Page<CatInfo> findAll(Pageable pageable);

    long countByAreaId(int areaId);
    long countByCampusId(int campusId);
}