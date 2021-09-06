package ltd.hengpro.backend.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum implements CodeMessageEnum<Integer>{
    TOKEN_TO_USERDTO_NOT_FOUND(0,"UUID非法,未查找到对应用户"),
    USERID_TO_STAFF_NOT_FOUND(1,"用户尚未注册在Staff表中");

    private final Integer code;

    private final String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
