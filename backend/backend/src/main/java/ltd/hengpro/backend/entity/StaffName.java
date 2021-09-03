package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 工作人员名字JavaBean --> 对应staff_name
 */
@Entity
@Data
public class StaffName implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 工作人员id */
    @Id
    private String staffId;

    /** 工作人员名字 */
    private String staffName;

}