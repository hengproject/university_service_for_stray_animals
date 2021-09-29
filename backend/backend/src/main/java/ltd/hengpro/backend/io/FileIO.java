package ltd.hengpro.backend.io;

import java.io.*;

public class FileIO extends ServerIO{
    public FileInputStream getFileInputStream(String address) throws FileNotFoundException {
        File ioFile = getIOFile(address);
        return new FileInputStream(ioFile);

    }
    public <T extends InputStream> void storeAFile(String filename,T inputStream){
        storeAFile(filename,inputStream,2048);
    }
    public <T extends InputStream> void storeAFile(String filename,T inputStream,int bufferSize) {
        File ioFile = getIOFile(filename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream=new FileOutputStream(ioFile);
            byte[] buffer=new byte[bufferSize];
            int len;
            while (
                    (len=inputStream.read(buffer))
                            !=-1
            ){
                fileOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public InputStream inputAFile(String filename){
        File ioFile = getIOFile(filename);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(ioFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileInputStream;
    }

    public void delete(String filename){
         File ioFile = getIOFile(filename);
         ioFile.delete();
    }
}
