package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Question implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private String questionId;

    private String starterId;

    private String dealerId;

    //0 其他 1 猫咪信息有误
    private String questionCategory;

    private String questionDescription;

    private String questionRemark;

    //0 已提交 1 等待审核中 2 正在审核 3 已解决
    private int questionStatus;

    private Date startTime;

    private Date endTime;
}