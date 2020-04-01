package br.com.wcc.banco.repositorio.impl.csv;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import br.com.wcc.banco.repositorio.impl.csv.enums.CSVHeaders;

import java.math.BigDecimal;

public class ClienteRepositorioCSV extends CSVRepository implements ClienteRepositorio {
    private static final String CLIENTES_CSV = "clientes.csv";


    @Override
    public Cliente buscarPorId(Integer id) {
        return getById(id, CLIENTES_CSV, Headers.class, Cliente.class);
    }


    @Override
    public void salvar(Cliente entidade) {
    }

    enum Headers implements CSVHeaders<Headers> {
        Id {
            @Override
            public Class<?> getEntityClass() {
                return Integer.class;
            }
        },
        Nome {
            @Override
            public Class<?> getEntityClass() {
                return String.class;
            }
        },
        idConta {
            @Override
            public Class<?> getEntityClass() {
                return Integer.class;
            }
        },
        Salario {
            @Override
            public Class<?> getEntityClass() {
                return BigDecimal.class;
            }
        };


        @Override
        public Headers getId() {
            return Headers.Id;
        }
    }
}
