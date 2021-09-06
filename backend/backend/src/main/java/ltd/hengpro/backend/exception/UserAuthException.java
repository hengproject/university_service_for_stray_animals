package ltd.hengpro.backend.exception;

import ltd.hengpro.backend.enums.ExceptionEnum;

public class UserAuthException extends RuntimeException{
    private Integer code;
    public UserAuthException(ExceptionEnum exceptionEnum)
    {
        super(exceptionEnum.getMessage());

        this.code = exceptionEnum.getCode();
    }
    public UserAuthException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }
}
