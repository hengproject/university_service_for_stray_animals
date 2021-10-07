package ltd.hengpro.backend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String starterId;

    private String questionCategory;

    private String questionDescription;

    private String questionRemark;
}
