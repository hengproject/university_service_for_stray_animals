package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusDao extends JpaRepository<Campus,Integer> {
    Campus findByCampusName(String campusName);
}

