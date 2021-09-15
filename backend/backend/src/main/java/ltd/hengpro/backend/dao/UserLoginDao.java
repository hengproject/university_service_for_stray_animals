package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.UserLogin;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLoginDao extends JpaRepository<UserLogin,String> {
    @Override
    List<UserLogin> findAll();

    UserLogin findUserLoginByUserId(String userId);

    UserLogin findUserLoginByUsernameAndPassword(String username,String password);

    Page<UserLogin> findAll(Pageable pageable);

//    UserLogin findUserLoginByUsername(String username);

    Page<UserLogin> findUserLoginByUsernameLike(String usernameLike,Pageable pageable);

//    @Query("select count(*) from UserLogin u where u.username like :usernameLike")
//    Integer findUserLoginByUsernameLikeNum(@Param("usernameLike") String usernameLike);

    Long countByUsernameLike(String usernameLike);

    UserLogin findUserLoginByUsername(String username);

}
