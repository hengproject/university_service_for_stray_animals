package ltd.hengpro.backend.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {
    private static final long SerialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;
    public ResultVo(){}
    public ResultVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
