package br.com.wcc.banco.repositorio.impl;

import br.com.wcc.banco.file.ResourceFile;
import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ClienteRepositorioCSV implements ClienteRepositorio {
    private static final String CLIENTES_CSV = "clientes.csv";


    @Override
    public Cliente buscarPorId(Integer id) {
        try {
            final ResourceFile resourceFile = new ResourceFile();
            FileReader file = resourceFile.getFileReaderFromResource(CLIENTES_CSV);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(Headers.class)
                    .withFirstRecordAsHeader()
                    .parse(file);
            for (CSVRecord csvRecord : records) {
                Integer clientId = Integer.parseInt(csvRecord.get(Headers.Id));
                if (clientId.equals(id)) {
                    String nome = csvRecord.get(Headers.Nome);
                    Integer idConta = Integer.parseInt(csvRecord.get(Headers.idConta));
                    BigDecimal salario = new BigDecimal(csvRecord.get(Headers.Salario));
                    return new Cliente(id, nome, idConta, salario);
                }
            }
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void salvar(Cliente entidade) {
    }

    private enum Headers {
        Id,
        Nome,
        idConta,
        Salario;
    }
}
