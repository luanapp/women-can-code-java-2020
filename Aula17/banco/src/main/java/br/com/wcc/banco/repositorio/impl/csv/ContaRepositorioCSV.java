package br.com.wcc.banco.repositorio.impl.csv;

import br.com.wcc.banco.file.ResourceFile;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import br.com.wcc.banco.repositorio.impl.csv.enums.CSVHeaders;
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

public class ContaRepositorioCSV extends CSVRepository implements ContaRepositorio {
    private static final String CONTAS_CSV = "contas.csv";

    @Override
    public Conta buscarPorId(Integer id) {
        return getById(id, CONTAS_CSV, Headers.class, Conta.class);
    }

    @Override
    public void salvar(Conta conta) {
        final List<Conta> contas = getAll(CONTAS_CSV, Headers.class, Conta.class);
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

    private int getArrayIndex(List<Conta> contas, Conta conta) {
        for (int i = 0; i < contas.size(); i++) {
            if (conta.getId().equals(contas.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    enum Headers implements CSVHeaders<Headers> {
        id {
            @Override
            public Class<?> getEntityClass() {
                return Integer.class;
            }
        },
        tipo {
            @Override
            public Class<?> getEntityClass() {
                return Conta.Type.class;
            }
        },
        saldo {
            @Override
            public Class<?> getEntityClass() {
                return BigDecimal.class;
            }
        };


        @Override
        public Headers getId() {
            return id;
        }
    }
}
