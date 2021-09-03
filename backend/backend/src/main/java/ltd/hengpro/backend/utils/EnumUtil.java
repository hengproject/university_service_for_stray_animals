package ltd.hengpro.backend.utils;

import ltd.hengpro.backend.enums.CodeMessageEnum;

public class EnumUtil
{
    public static <T extends CodeMessageEnum> T getByCode(Integer code, Class<T> enumClass) throws Exception {
        // 遍历枚举
        for (T eachEnum : enumClass.getEnumConstants())
        {
            // 判断Code是否相等
            if (code.equals(eachEnum.getCode()))
            {
                return eachEnum;
            }
        }
        throw new Exception("枚举常量不存在");
    }
}