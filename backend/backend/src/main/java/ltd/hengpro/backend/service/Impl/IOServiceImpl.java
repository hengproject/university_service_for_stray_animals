package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.FileRecordDao;
import ltd.hengpro.backend.entity.FileRecord;
import ltd.hengpro.backend.enums.ExceptionEnum;
import ltd.hengpro.backend.exception.FileException;
import ltd.hengpro.backend.io.FileIO;
import ltd.hengpro.backend.service.IOService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class IOServiceImpl implements IOService {
    @Autowired
    FileRecordDao fileRecordDao;

    @Deprecated
    public <T extends InputStream> String store(T inputStream,String fileName){
        FileIO fileIO = new FileIO();
        String[] split = fileName.split("\\.");
        String filetype = split[split.length - 1];
        String uuid = UUIDUtil.getUUID();
        String newFileName = uuid + "." + filetype;
        fileIO.storeAFile(newFileName,inputStream);
        return newFileName;
    }

    public <T extends InputStream> FileRecord storeAFile2FileRecord(T inputStream, String fileName){
        FileIO fileIO = new FileIO();
        String[] split = fileName.split("\\.");
        String filetype = split[split.length - 1];
        String uuid = UUIDUtil.getUUID();
        String newFileName = uuid + "." + filetype;
        fileIO.storeAFile(newFileName,inputStream);
        FileRecord fileRecord = new FileRecord();
        String fileId = UUIDUtil.getUUID();
        fileRecord.setFileId(fileId);
        fileRecord.setFileAdd(newFileName);
        fileRecordDao.saveAndFlush(fileRecord);
        return fileRecord;
    }

    public InputStream getImg(String fileName) throws FileNotFoundException {
        String[] split = fileName.split("\\.");
        String filetype = split[split.length - 1];
        if(filetype.equalsIgnoreCase("jpg")||filetype.equalsIgnoreCase("png")){
            FileIO fileIO = new FileIO();
            return fileIO.getFileInputStream(fileName);
        }else throw new FileException(ExceptionEnum.NOT_IMG_EXCEPTION);
    }

    public void deleteFile(String fileId){
        FileRecord fileRecord = fileRecordDao.findById(fileId).get();
        if(fileRecord.getFileId().equals("0"))return;
        fileRecordDao.delete(fileRecord);
        FileIO fileIO = new FileIO();
        fileIO.delete(fileRecord.getFileAdd());
    }

    @Override
    public String getFileAddr(String fileId) {
        return fileRecordDao.findById(fileId).get().getFileAdd();
    }

}
