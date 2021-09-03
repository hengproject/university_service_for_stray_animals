package ltd.hengpro.backend.vo;

import lombok.Data;

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

}