package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "cat_info")
@Entity
@Data
public class CatInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private String catId;

    private int catSex;

    private int catAge;

    private Date createTime;

    private Date updateTime;

    private Date lastFindTime;

    private String catPhotoId;

    private String catRemark;

    private int campusId;

    private int areaId;

    private String catStatus;

    private String catName;
}