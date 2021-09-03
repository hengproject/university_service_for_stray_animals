package ltd.hengpro.backend.enums;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
public enum SpecialIdentityEnum implements CodeMessageEnum<Integer>{
    NORMAL_USER(0,"空"),
    CREATER(1,"创建者，最高权限");


    private Integer code;

    private String message;

    SpecialIdentityEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
