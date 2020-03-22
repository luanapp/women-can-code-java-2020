package br.com.wcc.banco.repositorio.impl;

import br.com.wcc.banco.file.ResourceFile;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaRepositorioCSV implements ContaRepositorio {
    private static final String CONTAS_CSV = "contas.csv";

    @Override
    public void salvar(Conta conta) {
        final List<Conta> contas = readContentAsList();
        contas.set(getArrayIndex(contas, conta), conta);
        try {
            final ResourceFile resourceFile = new ResourceFile();
            final FileWriter file = resourceFile.getFileWriterFromResource(CONTAS_CSV);
            final CSVPrinter printer = new CSVPrinter(file, CSVFormat.DEFAULT.withHeader(Headers.class));

            for (Conta c : contas) {
                printer.printRecord(c.getId(), c.getTipoConta(), c.getSaldo().toPlainString());
            }
            file.close();
        } catch (IOException ignored) {

        }
    }

    @Override
    public Conta buscarPorId(Integer id) {
        try {
            final ResourceFile resourceFile = new ResourceFile();
            final FileReader file = resourceFile.getFileReaderFromResource(CONTAS_CSV);
            Conta conta = null;

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader()
                    .withFirstRecordAsHeader()
                    .parse(file);
            for (CSVRecord csvRecord : records) {
                Integer contaId = Integer.parseInt(csvRecord.get(Headers.id));
                if (contaId.equals(id)) {
                    final Conta.Type tipo = Conta.Type.valueOf(csvRecord.get(Headers.tipo));
                    final BigDecimal saldo = new BigDecimal(csvRecord.get(Headers.saldo));
                    conta = new Conta(id, tipo, saldo);
                }
            }
            file.close();
            return conta;
        } catch (IOException ex) {
            return null;
        }
    }

    private List<Conta> readContentAsList() {
        try {
            final List<Conta> contas = new ArrayList<>();

            final ResourceFile resourceFile = new ResourceFile();
            final FileReader file = resourceFile.getFileReaderFromResource(CONTAS_CSV);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader()
                    .withFirstRecordAsHeader()
                    .parse(file);
            for (CSVRecord csvRecord : records) {
                final Integer contaId = Integer.parseInt(csvRecord.get(Headers.id));
                final Conta.Type tipo = Conta.Type.valueOf(csvRecord.get(Headers.tipo));
                final BigDecimal saldo = new BigDecimal(csvRecord.get(Headers.saldo));
                contas.add(new Conta(contaId, tipo, saldo));
            }
            file.close();
            return contas;
        } catch (IOException ex) {
            return Collections.emptyList();
        }

    }

    private int getArrayIndex(List<Conta> contas, Conta conta) {
        for (int i = 0; i < contas.size(); i++) {
            if (conta.getId().equals(contas.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public enum Headers {
        id,
        tipo,
        saldo
    }
}
