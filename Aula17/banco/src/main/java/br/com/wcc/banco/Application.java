package br.com.wcc.banco;

import br.com.wcc.banco.injector.RepositoryInjector;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.model.Operacao;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ContaServico;
import br.com.wcc.banco.servico.ExecutorOperacao;
import br.com.wcc.banco.servico.impl.ClienteServicoImpl;
import br.com.wcc.banco.servico.impl.ContaServicoImpl;

import static br.com.wcc.banco.injector.RepositoryInjector.Type.CSV;

public class Application {


    public static void main(String[] args) {
        RepositoryInjector repositoryInjector = new RepositoryInjector();
        ClienteServico clienteServico = new ClienteServicoImpl(repositoryInjector.getClienteRepositorio(CSV));
        ContaServico contaServico = new ContaServicoImpl(repositoryInjector.getContaRepositorio(CSV));
        ExecutorOperacao executorOperacao = new ExecutorOperacao() {
            @Override
            public String executarOperacaoEmConta(Operacao operacao, Conta conta) {
                return "Operação executada com sucesso?";
            }
        };
        new ConsoleApplicaton(clienteServico, contaServico, executorOperacao).start();
    }


}
