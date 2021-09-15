package ltd.hengpro.backend.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum implements CodeMessageEnum<Integer>{
    TOKEN_TO_USERDTO_NOT_FOUND(0,"TOKEN非法,未查找到对应用户"),
    USERID_TO_STAFF_NOT_FOUND(1,"用户尚未注册在Staff表中"),
    DELETE_INFO_DAO_NOT_FOUND(2,"删除用户时,该表未发现"),
    EDIT_USER_NOT_FOUND(3,"修改用户信息时,该用户不存在");

    private final Integer code;

    private final String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
