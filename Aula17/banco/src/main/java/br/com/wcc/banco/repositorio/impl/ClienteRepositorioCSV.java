package br.com.wcc.banco.repositorio.impl;

import br.com.wcc.banco.file.ResourceFileReader;
import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class ClienteRepositorioCSV implements ClienteRepositorio {

    private static final String CLIENTES_CSV = "clientes.csv";

    @Override
    public Cliente buscaClientePorId(Integer id) {
        try {
            final ResourceFileReader resourceFileReader = new ResourceFileReader();
            FileReader file = resourceFileReader.getFileReaderFromResource(CLIENTES_CSV);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(file);
            for (CSVRecord csvRecord : records) {
                Integer clientId = Integer.parseInt(csvRecord.get("Id"));
                if (clientId.equals(id)) {
                    String nome = csvRecord.get("Nome");
                    Integer idConta = Integer.parseInt(csvRecord.get("idConta"));
                    return new Cliente(id, nome, idConta);
                }
            }
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
