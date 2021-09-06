package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.StaffInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffDao extends JpaRepository<StaffInfo,String> {
    StaffInfo findByUserId(String userId);

}
