package ltd.hengpro.backend.utils;

import java.util.UUID;

public class UUIDUtil {

    public static synchronized String getUUID(){
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");

        return uid;
    }
}
