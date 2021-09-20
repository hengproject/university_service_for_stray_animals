package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 活动区域JavaBean --> 对应area
 */
@Entity
@Data
public class Area implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 活动区域id */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer areaId;

    /** 活动区域名称 */
    private String areaName;

    /** 校区id */
    private Integer campusId;

}
