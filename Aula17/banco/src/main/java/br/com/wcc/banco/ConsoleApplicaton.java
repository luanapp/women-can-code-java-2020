package br.com.wcc.banco;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ContaServico;
import br.com.wcc.banco.servico.ExecutorOperacao;

import java.util.Scanner;

public class ConsoleApplicaton {
    private static final String FORMATO_OPCAO = "%d: %s";
    private static final OpcaoOperacao[] OPERACOES = OpcaoOperacao.values();

    private Scanner scanner;
    private ClienteServico clienteServico;
    private ContaServico contaServico;
    private ExecutorOperacao executorOperacao;

    ConsoleApplicaton(ClienteServico clienteServico, ContaServico contaServico, ExecutorOperacao executorOperacao) {
        this.clienteServico = clienteServico;
        this.contaServico = contaServico;
        this.executorOperacao = executorOperacao;
    }

    void start() {
        scanner = new Scanner((System.in));

        Cliente cliente = selecionarCliente();
        Conta conta = contaServico.buscarPorId(cliente.getIdConta());

        for (; ; ) {
            OpcaoOperacao opcaoOperacao = selecionarOperacao();
            final String resultadoOperacao =
                    executorOperacao.executarOperacaoEmConta(null, null);
            System.out.println(resultadoOperacao);
            System.out.println("\nDeseja executar outra operaçãp? (S ou N)");
            final String continuar = scanner.next();
            if (continuar.equalsIgnoreCase("N")) {
                scanner.close();
                System.exit(0);
            }
            System.out.println("\n");
        }
    }

    private Cliente selecionarCliente() {
        Cliente cliente;
        for (; ; ) {
            System.out.println("Digite o id do cliente:");
            if (isNumeroValido()) continue;

            cliente = clienteServico.buscarPorId(scanner.nextInt());
            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                continue;
            }
            break;
        }
        System.out.println("Cliente selecionado: " + cliente.getNome());
        return cliente;
    }

    private OpcaoOperacao selecionarOperacao() {
        OpcaoOperacao opcaoOperacao;
        for (; ; ) {
            exibeOpcoes();

            if (isNumeroValido()) continue;

            final int opcao = scanner.nextInt();
            if (opcao <= 0 || opcao > OPERACOES.length + 1) {
                System.out.println("Opção inválida: " + scanner.nextLine());
                continue;
            }

            opcaoOperacao = traduzirOpcaoOperacao(opcao);

            System.out.println("Operação escolhida: " + opcaoOperacao.getTexto());
            break;
        }
        return opcaoOperacao;
    }

    private boolean isNumeroValido() {
        if (!scanner.hasNextInt()) {
            System.out.println("Opção inválida: " + scanner.nextLine());
            return true;
        }
        return false;
    }

    private void exibeOpcoes() {
        System.out.println("Selecione uma operação:");
        for (int i = 0; i < OpcaoOperacao.values().length; i++) {
            OpcaoOperacao opcaoOperacao = OPERACOES[i];
            System.out.println(String.format(FORMATO_OPCAO, i + 1, opcaoOperacao.getTexto()));
        }
        System.out.println(String.format(FORMATO_OPCAO, OpcaoOperacao.values().length + 1, "Sair"));
    }

    private OpcaoOperacao traduzirOpcaoOperacao(int opcao) {
        if (opcao == OPERACOES.length + 1) {
            System.out.println("Saindo...");
            scanner.close();
            System.exit(0);
        }

        return OPERACOES[opcao - 1];
    }

    private enum OpcaoOperacao {
        SAQUE("Saque"),
        SALDO("Saldo"),
        DEPOSITO("Depósito");

        private String texto;

        OpcaoOperacao(String texto) {
            this.texto = texto;
        }

        public String getTexto() {
            return texto;
        }
    }
}
