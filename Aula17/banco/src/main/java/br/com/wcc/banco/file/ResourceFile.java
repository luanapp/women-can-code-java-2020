package br.com.wcc.banco.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceFile {

    public FileReader getFileReaderFromResource(String filename) throws FileNotFoundException {
        try {
            URI fileURI = getFileURI(filename);
            return new FileReader(fileURI.getPath());
        } catch (URISyntaxException e) {
            throw new FileNotFoundException(filename);
        }
    }

    public FileWriter getFileWriterFromResource(String fileName) throws FileNotFoundException {
        try {
            URI fileURI = getFileURI(fileName);
            return new FileWriter(fileURI.getPath());
        } catch (URISyntaxException | IOException e) {
            throw new FileNotFoundException(fileName);
        }
    }

    private URI getFileURI(String filename) throws URISyntaxException {
        final URL resourceUrl = getClass().getClassLoader().getResource(filename);
        assert resourceUrl != null;
        return new URI(resourceUrl.getPath());
    }
}
