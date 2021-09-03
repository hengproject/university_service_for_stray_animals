package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 喂食JavaBean --> 对应feed
 */
@Entity
@Data
public class feed implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 喂食id */
    @Id
    private String feedId;

    /** 校区Id */
    private String campusId;

    /** 活动区域Id */
    private String areaId;

    /** 工作人员Id */
    private String staffId;

    /** 喂食时间 */
    private Date feedTime;

    /** 备注 */
    private String feedRemark;

}
