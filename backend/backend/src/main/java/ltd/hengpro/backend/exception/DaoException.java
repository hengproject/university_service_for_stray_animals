package ltd.hengpro.backend.exception;

import ltd.hengpro.backend.enums.ExceptionEnum;

public class DaoException extends RuntimeException{
    private Integer code;
    public DaoException(ExceptionEnum exceptionEnum)
    {
        super(exceptionEnum.getMessage());

        this.code = exceptionEnum.getCode();
    }
    public DaoException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }
}