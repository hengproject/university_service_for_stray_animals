package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户名JavaBean --> 对应user_name
 */
@Entity
@Data
public class UserLogin implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Id
    private String userId;

    /** 用户名 */
    private String username;

    /** 用户密码 */
    private String password;

    public UserLogin() {
    }

    public UserLogin(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}