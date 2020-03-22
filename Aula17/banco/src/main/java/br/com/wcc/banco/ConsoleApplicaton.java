package br.com.wcc.banco;

import br.com.wcc.banco.model.Cliente;
import br.com.wcc.banco.model.Conta;
import br.com.wcc.banco.operacao.DepositoOperacao;
import br.com.wcc.banco.operacao.Operacao;
import br.com.wcc.banco.operacao.SaldoOperacao;
import br.com.wcc.banco.operacao.SaqueOperacao;
import br.com.wcc.banco.servico.ClienteServico;
import br.com.wcc.banco.servico.ContaServico;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleApplicaton {
    private static final String FORMATO_OPCAO = "%d: %s";
    private static final Operacao.Type[] OPERACOES = Operacao.Type.values();

    private Scanner scanner;
    private ClienteServico clienteServico;
    private ContaServico contaServico;

    ConsoleApplicaton(ClienteServico clienteServico, ContaServico contaServico) {
        this.clienteServico = clienteServico;
        this.contaServico = contaServico;
    }

    void start() {
        scanner = new Scanner((System.in));

        Cliente cliente = selecionarCliente();
        Conta conta = contaServico.buscarPorId(cliente.getIdConta());

        for (; ; ) {
            final Operacao.Type tipoOperacao = selecionarOperacao();
            final BigDecimal resultadoOperacao = executarOperacao(tipoOperacao, cliente, conta);
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

    private Operacao.Type selecionarOperacao() {
        Operacao.Type tipoOperacao;
        for (; ; ) {
            exibeOpcoes();

            if (isNumeroValido()) continue;

            final int opcao = scanner.nextInt();
            if (opcao <= 0 || opcao > OPERACOES.length + 1) {
                System.out.println("Opção inválida: " + scanner.nextLine());
                continue;
            }

            tipoOperacao = traduzirOpcaoOperacao(opcao);

            System.out.println("Operação escolhida: " + tipoOperacao.getTexto());
            break;
        }
        return tipoOperacao;
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
        for (int i = 0; i < Operacao.Type.values().length; i++) {
            Operacao.Type tipoOperacao = OPERACOES[i];
            System.out.println(String.format(FORMATO_OPCAO, i + 1, tipoOperacao.getTexto()));
        }
        System.out.println(String.format(FORMATO_OPCAO, Operacao.Type.values().length + 1, "Sair"));
    }

    private Operacao.Type traduzirOpcaoOperacao(int opcao) {
        if (opcao == OPERACOES.length + 1) {
            System.out.println("Saindo...");
            scanner.close();
            System.exit(0);
        }

        return OPERACOES[opcao - 1];
    }

    private BigDecimal executarOperacao(Operacao.Type tipo, Cliente cliente, Conta conta) {
        final String textoParametro = "Digite o valor do %s:";
        final String textoParametroInvalido = "Valor do %s inválido! Use ponto(.) como separador decimal";
        for (; ; ) {
            try {

                switch (tipo) {
                    case DEPOSITO:
                        System.out.println(String.format(textoParametro, tipo.getTexto().toLowerCase()));
                        final BigDecimal deposito = new BigDecimal(scanner.next());
                        return new DepositoOperacao(contaServico).executar(cliente, conta, deposito);
                    case SAQUE:
                        System.out.println(String.format(textoParametro, tipo.getTexto().toLowerCase()));
                        final BigDecimal saque = new BigDecimal(scanner.next());
                        return new SaqueOperacao(contaServico).executar(cliente, conta, saque);
                    case SALDO:
                        return new SaldoOperacao().executar(cliente, conta, null);
                    default:
                        return null;
                }
            } catch (Exception ex) {
                System.out.println(String.format(textoParametroInvalido, tipo.getTexto().toLowerCase()));
            }
        }
    }
}
