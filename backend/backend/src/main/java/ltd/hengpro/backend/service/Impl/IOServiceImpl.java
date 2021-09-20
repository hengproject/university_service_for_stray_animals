package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.io.FileIO;
import ltd.hengpro.backend.service.IOService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class IOServiceImpl implements IOService {

    public <T extends InputStream> String store(T inputStream,String fileName){
        FileIO fileIO = new FileIO();
        String[] split = fileName.split("\\.");
        String filetype = split[split.length - 1];
        String uuid = UUIDUtil.getUUID();
        String newFileName = uuid + "." + filetype;
        fileIO.storeAFile(newFileName,inputStream);
        return newFileName;
    }

}
