package br.com.wcc.banco;

import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.model.Operacao;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import br.com.wcc.banco.repositorio.ClienteRepositorioCSV;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ClienteServicoImpl;
import br.com.wcc.banco.servico.ExecutorOperacao;

public class    Application {


    public static void main(String[] args) {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorioCSV();
        ClienteServico clienteServico = new ClienteServicoImpl(clienteRepositorio);
        ExecutorOperacao executorOperacao = new ExecutorOperacao() {
            @Override
            public String executarOperacaoEmConta(Operacao operacao, Conta conta) {
                throw new UnsupportedOperationException();
            }
        };
        new ConsoleApplicaton(clienteServico, executorOperacao).start();
    }


}
