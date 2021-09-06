package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLoginDao extends JpaRepository<UserLogin,String> {
    @Override
    List<UserLogin> findAll();

    UserLogin findUserLoginByUsernameAndPassword(String username,String password);

    Page<UserLogin> findAll(Pageable pageable);

}
