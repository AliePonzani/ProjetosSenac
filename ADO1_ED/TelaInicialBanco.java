import javax.swing.JOptionPane;

public class TelaInicialBanco {
    public static void main(String[] args) throws Exception {
        int escolha = 0;
        String menu = "Escolha uma opção \n 1. Cadastrar nova conta\n 2.Exibir dados da conta e saldo\n 3.Realizar Depósito\n 4.Transferência\n 5.Excluir conta";
        Banco banco = new Banco(5);
        Contas contas = new Contas();
        int conta = 1000;

        do {
            escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (escolha) {
                case 1:
                    String nome = JOptionPane.showInputDialog("Digite seu nome:");
                    int idade = Integer.parseInt((JOptionPane.showInputDialog("Digite sua idade:")));
                    String telefone = JOptionPane.showInputDialog("Digite seu telefone:");
                    String endereco = JOptionPane.showInputDialog("Digite seu endereço:");
                    conta++;

                    contas = new Contas(nome, idade, telefone, endereco, conta);
                    try {
                        banco.adiciona(contas);
                        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!!\nSua conta é: " + conta);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    Contas contaEncontrada = banco.buscarConta(
                            Integer.parseInt((JOptionPane.showInputDialog("Digite a conta:"))));
                    if (contaEncontrada != null) {
                        JOptionPane.showMessageDialog(null, "Informações da conta:\n"
                                + "Nome: " + contaEncontrada.getNome()
                                + "\nIdade: " + contaEncontrada.getIdade()
                                + "\nEndereço: " + contaEncontrada.getEndereco()
                                + "\nSaldo: R$" + contaEncontrada.getSaldo());

                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    }
                    break;

                case 3:// deposito
                    try {
                        banco.deposito(
                                Integer.parseInt(
                                        (JOptionPane
                                                .showInputDialog("Digite a conta que deseja realizar o depósito:"))),
                                Double.parseDouble((JOptionPane.showInputDialog("Valor do depósito:"))));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    break;

                case 4:// Transferência
                    try {
                        banco.transferencia(
                                Integer.parseInt((JOptionPane.showInputDialog("Digite sua conta:"))),
                                Integer.parseInt((JOptionPane
                                        .showInputDialog("Realizar tranferência para a conta:"))),
                                Double.parseDouble((JOptionPane.showInputDialog("Valor da transferência:"))));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    break;

                case 5:// Consultar Saldo
                    try {
                        banco.excluirConta(
                                Integer.parseInt((JOptionPane.showInputDialog("Digite a conta que deseja excluir:"))));
                        JOptionPane.showMessageDialog(null, "Conta excluida com sucesso!!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!!!");
                    break;
            }
            escolha = JOptionPane.showConfirmDialog(null, "Deseja realizar algum outro serviço?");
        } while (escolha == 0);
    }
}