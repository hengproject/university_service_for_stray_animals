package ltd.hengpro.backend.service;

import java.io.InputStream;

public interface IOService {
    <T extends InputStream> String store(T inputStream, String fileName);
}
