package ltd.hengpro.backend.service;

import ltd.hengpro.backend.entity.FileRecord;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IOService {
    <T extends InputStream> String store(T inputStream, String fileName);

    InputStream getImg(String fileName) throws FileNotFoundException;

    @Deprecated
    String getFileAddr(String fileId);

    <T extends InputStream> FileRecord storeAFile2FileRecord(T inputStream, String fileName);

    void deleteFile(String fileId);
}
