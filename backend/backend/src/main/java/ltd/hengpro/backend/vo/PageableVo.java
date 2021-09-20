package ltd.hengpro.backend.vo;

import lombok.Data;

@Data
public class PageableVo {
    private Integer pageNum;
    private Integer pageSize;
    private String query;
}
