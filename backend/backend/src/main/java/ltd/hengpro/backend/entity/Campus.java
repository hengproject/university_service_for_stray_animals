package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 校区JavaBean --> 对应campus
 */
@Entity
@Data
public class Campus implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 校区id */
    @Id
    private String campusId;

    /** 校区名称 */
    private String campusName;

}
