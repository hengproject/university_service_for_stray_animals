package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 工作人员统计JavaBean --> 对应staff_statistics
 */
@Entity
@Data
public class StaffStatistics implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 工作人员id */
    @Id
    private String staffId;

    /** 工作人员总登录天数 */
    private String totalLogn;

    /** 工作人员本月登录天数 */
    private String thismonthLogn;

    /** 工作人员上月登录天数 */
    private String lastmonthLogn;

    /** 工作人员本周登录天数 */
    private String thisweekLogn;

    /** 工作人员上周登录天数 */
    private String lastweekLogn;

    /** 工作人员最近登录天数 */
    private String latestLogn;

}