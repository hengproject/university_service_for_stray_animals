package ltd.hengpro.backend.io;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class TextIO extends ServerIO{
    private MultipartFile multipartFile;
    public void write(String text,String address) throws IOException {
        File ioFile = getIOFile(address);
        FileWriter fileWriter = new FileWriter(ioFile);
        fileWriter.write(text);
        fileWriter.close();
    }

    public String read(String address) throws IOException {


        File file=getIOFile(address);
        char[] cbuf = new char[5];
        System.out.println(file.isFile());
        FileReader fileReader=new FileReader(file);
        fileReader.read(cbuf);
        fileReader.close();
        return String.valueOf(cbuf);
    }



}
