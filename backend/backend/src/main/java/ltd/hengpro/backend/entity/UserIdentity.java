package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户权限JavaBean --> 对应user_root
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
    private Integer group;

    /** 用户特殊身份 */
    private Integer specialIdentity;

}