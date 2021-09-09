package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户权限JavaBean --> 对应user_identity
 */
@Entity
@Data
public class UserIdentity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Id
    private String userId;

    /** 用户分组 */
    private Integer userGroup;


    /** 用户特殊身份 */
    private Integer specialIdentity;

    private String staffId;

    public UserIdentity(String userId, Integer userGroup, Integer specialIdentity, String staffId) {
        this.userId = userId;
        this.userGroup = userGroup;
        this.specialIdentity = specialIdentity;
        this.staffId = staffId;
    }

    public UserIdentity() {
    }
}