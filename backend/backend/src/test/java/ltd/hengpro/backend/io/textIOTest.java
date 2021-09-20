package ltd.hengpro.backend.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class textIOTest {

    @Test
    void write() throws IOException {
        TextIO TextIO = new TextIO();
        TextIO.write("i have a dream","hello.txt");

    }

    @Test
    void read() throws IOException {
        TextIO TextIO = new TextIO();
        String read = TextIO.read("hello.txt");
        System.out.println(read);
    }
}