package br.com.wcc.banco.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class ResourceFileReader {

    public FileReader getFileReaderFromResource(String fileName) throws FileNotFoundException {
        final URL resourceUrl = getClass().getClassLoader().getResource(fileName);
        assert resourceUrl != null;
        return new FileReader(resourceUrl.getPath());
    }
}
