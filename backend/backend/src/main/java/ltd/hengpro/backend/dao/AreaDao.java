package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaDao extends JpaRepository<Area, Integer> {
    List<Area> findByCampusId(Integer campusId);
}