package ltd.hengpro.backend.dto;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Data
public class CatDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String catId;

    private int catSex;

    private int catAge;

    private Date createTime;

    private Date updateTime;

    private Date lastFindTime;

    private String catPhotoId;

    private String catRemark;

    private String campusName;

    private String areaName;

    private String catStatus;

    private String catName;

    private int campusId;

    private int areaId;
}
