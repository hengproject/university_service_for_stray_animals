package ltd.hengpro.backend.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CatAbbrDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String catId;

    private String catName;

    private Date lastFindTime;

    private String catPhotoId;

    private int catSex;

    private int catAge;

    private String catRemark;

    private String campusName;

    private String areaName;

    private String catStatus;

    private Date createTime;
}
