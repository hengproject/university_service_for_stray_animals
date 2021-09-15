package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

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
    private Long totalLogin;

    /** 工作人员本月登录天数 */
    private Long thisMonthLogin;

    /** 工作人员上月登录天数 */
    private Long lastMonthLogin;

    /** 工作人员本周登录天数 */
    private Long thisWeekLogin;

    /** 工作人员上周登录天数 */
    private Long lastWeekLogin;

    /** 工作人员最近登录时间 */
    private Date latestLogin;

}