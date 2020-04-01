package br.com.wcc.banco;

import br.com.wcc.banco.injector.RepositoryInjector;
import br.com.wcc.banco.injector.ServiceInjector;
import br.com.wcc.banco.repositorio.impl.csv.ClienteRepositorioCSV;
import br.com.wcc.banco.repositorio.impl.csv.ContaRepositorioCSV;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ContaServico;
import br.com.wcc.banco.servico.impl.ClienteServicoImpl;
import br.com.wcc.banco.servico.impl.ContaServicoImpl;

import static br.com.wcc.banco.injector.RepositoryInjector.Type.CSV;

public class Application {


    public static void main(String[] args) {
        final RepositoryInjector repositoryInjector = new RepositoryInjector();
        final ServiceInjector serviceInjector = new ServiceInjector();

        final RepositoryInjector.Type type = CSV;
        ClienteServico clienteServico = serviceInjector.getServico(ClienteServicoImpl.class,
                repositoryInjector.getRepositorio(type, ClienteRepositorioCSV.class));
        ContaServico contaServico = serviceInjector.getServico(ContaServicoImpl.class,
                repositoryInjector.getRepositorio(type, ContaRepositorioCSV.class));

        new ConsoleApplicaton(clienteServico, contaServico).start();
    }


}
