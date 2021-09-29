package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.CatInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatInfoDao extends JpaRepository<CatInfo, String> {
    @Override
    Page<CatInfo> findAll(Pageable pageable);
}