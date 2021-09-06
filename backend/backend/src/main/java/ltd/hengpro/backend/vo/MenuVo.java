package ltd.hengpro.backend.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String authName;

    public Integer id;

    public List<MenuVo> children;

}