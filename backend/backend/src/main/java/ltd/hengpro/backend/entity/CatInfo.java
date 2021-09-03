package ltd.hengpro.backend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 猫属性JavaBean --> 对应cat_attribute
 */
@Entity
@Data
@DynamicUpdate
public class CatInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 猫id */
    @Id
    private String catId;

    /** 猫性别 */
    private String catSex;

    /** 猫年龄 */
    private String catAge;

    /**  创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /** 最后发现时间 */
    private Date lastfindTime;

    /** 猫照片 */
    private String catPhoto;

    /** 备注 */
    private String catRemark;

    /** 活动区域Id */
    private String areaId;

    /** 校区Id */
    private String campusId;

    /** 猫状态 */
    private String catStatus;

}