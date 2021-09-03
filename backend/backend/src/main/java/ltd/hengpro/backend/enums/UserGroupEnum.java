package ltd.hengpro.backend.enums;

import lombok.Getter;

@Getter
public enum UserGroupEnum implements CodeMessageEnum<Integer>{
    SUPER_USER(0,"超级用户"),
    NORMAL_USER(1,"普通用户");


    private Integer code;

    private String message;

    UserGroupEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
