package ltd.hengpro.backend.form.manager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EditCatForm implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String catId;

    private int catSex;

    private int catAge;

    private String catRemark;

    private int campusId;

    private int areaId;

    private String catStatus;

    private String catName;
}
