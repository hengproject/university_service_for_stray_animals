package ltd.hengpro.backend.vo;

import lombok.Data;
import ltd.hengpro.backend.entity.UserLogin;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String username;

    /** 用户密码 */
    private String password;

    public UserLoginVo() {
    }

    public UserLoginVo(UserLogin userLogin) {
        this.username = userLogin.getUsername();
        this.password = userLogin.getPassword();
    }

    public UserLoginVo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}