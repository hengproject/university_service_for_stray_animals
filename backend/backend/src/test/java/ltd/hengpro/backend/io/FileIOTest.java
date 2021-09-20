package ltd.hengpro.backend.io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    @Test
    void getFileOutputStream() {
    }

    @Test
    void storeAFile() throws IOException {
        FileIO fileIO = new FileIO();
//        FileInputStream fileOutputStream = fileIO.getFileInputStream("hello.txt");
//        fileIO.storeAFile("2.txt",fileOutputStream,2048);
        FileInputStream fileOutputStream = fileIO.getFileInputStream("支付宝付款码.jpg");
        fileIO.storeAFile("付款码.jpg",fileOutputStream,2048);

    }
}