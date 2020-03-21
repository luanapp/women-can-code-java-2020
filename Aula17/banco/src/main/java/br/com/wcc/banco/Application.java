package br.com.wcc.banco;

import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.model.Operacao;
import br.com.wcc.banco.repositorio.ClienteRepositorio;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import br.com.wcc.banco.repositorio.impl.ClienteRepositorioCSV;
import br.com.wcc.banco.repositorio.impl.ContaRepositorioCSV;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ContaServico;
import br.com.wcc.banco.servico.ExecutorOperacao;
import br.com.wcc.banco.servico.impl.ClienteServicoImpl;
import br.com.wcc.banco.servico.impl.ContaServicoImpl;

public class Application {


    public static void main(String[] args) {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorioCSV();
        ClienteServico clienteServico = new ClienteServicoImpl(clienteRepositorio);
        ContaRepositorio contaRepositorio = new ContaRepositorioCSV();
        ContaServico contaServico = new ContaServicoImpl(contaRepositorio);
        ExecutorOperacao executorOperacao = new ExecutorOperacao() {
            @Override
            public String executarOperacaoEmConta(Operacao operacao, Conta conta) {
                return "Operação executada com sucesso?";
            }
        };
        new ConsoleApplicaton(clienteServico, contaServico, executorOperacao).start();
    }


}
