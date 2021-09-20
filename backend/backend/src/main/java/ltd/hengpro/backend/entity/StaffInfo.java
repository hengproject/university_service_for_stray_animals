package ltd.hengpro.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 工作人员JavaBean --> 对应staff_attribute
 */
@Entity
@Data
@DynamicUpdate
public class StaffInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 工作人员id */
    @Id
    private String staffId;

    /** 工作人员性别 */
    private String staffSex;

    /** 工作人员年龄 */
    private String staffAge;

    /**  创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    /** 修改时间 */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /** 工作人员身份 */
    private Integer staffIdentity;

    /** 备注 */
    private String staffRemark;

    private String userId;

    private String staffName;

    private String qq;

    private String phoneNum;
    public StaffInfo() {
    }

    public StaffInfo(String staffId, String staffSex, String staffAge, Date createTime, Date updateTime, Integer staffIdentity, String staffRemark, String userId, String staffName) {
        this.staffId = staffId;
        this.staffSex = staffSex;
        this.staffAge = staffAge;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.staffIdentity = staffIdentity;
        this.staffRemark = staffRemark;
        this.userId = userId;
        this.staffName = staffName;
    }
}
