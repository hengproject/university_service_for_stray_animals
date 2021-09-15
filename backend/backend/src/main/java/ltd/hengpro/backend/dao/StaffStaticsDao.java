package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.StaffStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffStaticsDao extends JpaRepository<StaffStatistics,String> {
    StaffStatistics findByStaffId(String staffId);

}
