package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 猫统计JavaBean --> 对应cat_statistics
 */
@Entity
@Data
public class CatStatistics implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 猫id */
    @Id
    private String catId;

    /** 猫出现次数 */
    private String showtimes;

}
