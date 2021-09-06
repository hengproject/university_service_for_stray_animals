package ltd.hengpro.backend.enums;

import lombok.Getter;

@Getter
public enum StaffIdentityEnum implements CodeMessageEnum<Integer>{
    MANAGER(0,"管理者"),
    NORMAL_USER(1,"普通用户");


    private Integer code;

    private String message;

    StaffIdentityEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}

