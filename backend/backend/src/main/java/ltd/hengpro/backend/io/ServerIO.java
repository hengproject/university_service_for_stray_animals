package ltd.hengpro.backend.io;

import java.io.File;

public class ServerIO {
    protected File getIOFile(String address){
        return new File("./src/main/resources/IO/"+address);
    }
}
