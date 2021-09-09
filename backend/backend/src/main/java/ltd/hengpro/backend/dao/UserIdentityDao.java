package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserIdentityDao extends JpaRepository<UserIdentity,String> {

    UserIdentity findByUserId(String userID);

}
