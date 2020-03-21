package br.com.wcc.banco.repositorio.impl;

import br.com.wcc.banco.file.ResourceFileReader;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class ContaRepositorioCSV implements ContaRepositorio {
    private static final String CONTAS_CSV = "contas.csv";

    @Override
    public void salvar(Conta conta) {

    }

    @Override
    public Conta buscarPorId(Integer id) {
        try {
            final ResourceFileReader resourceFileReader = new ResourceFileReader();
            FileReader file = resourceFileReader.getFileReaderFromResource(CONTAS_CSV);


            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(file);
            for (CSVRecord csvRecord : records) {
                Integer contaId = Integer.parseInt(csvRecord.get("id"));
                if (contaId.equals(id)) {
                    return new Conta();
                }
            }
            return null;
        } catch (IOException ex) {
            return null;
        }
    }
}
