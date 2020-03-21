package br.com.wcc.banco.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceFileReader {

    public FileReader getFileReaderFromResource(String fileName) throws FileNotFoundException {
        final URL resourceUrl = getClass().getClassLoader().getResource(fileName);
        assert resourceUrl != null;
        try {
            URI fileURI = new URI(resourceUrl.getPath());
            return new FileReader(fileURI.getPath());
        } catch (URISyntaxException e) {
            throw new FileNotFoundException(fileName);
        }
    }
}
