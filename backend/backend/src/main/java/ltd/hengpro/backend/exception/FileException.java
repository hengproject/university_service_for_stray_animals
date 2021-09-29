package ltd.hengpro.backend.exception;

import ltd.hengpro.backend.enums.ExceptionEnum;

public class FileException extends RuntimeException{
    private Integer code;
    public FileException(ExceptionEnum exceptionEnum)
    {
        super(exceptionEnum.getMessage());

        this.code = exceptionEnum.getCode();
    }
    public FileException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }

}
