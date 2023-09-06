public class Banco {
    private int tamanho;
    private int posicao;
    private Contas[] contas;

    public Banco(int capacidade) {
        this.contas = new Contas[capacidade];
        this.tamanho = 0;
    }

    public void adiciona(Contas contas) throws Exception {
        aumentaCapacidade();
        if (this.tamanho < this.contas.length) {
            this.contas[this.tamanho] = contas;
            this.tamanho++;
        } else {
            throw new Exception("O Vetor já está cheio, "
                    + "não é possível adiconar novos elementos");
        }
    }

    public int tamanho() {
        return this.tamanho;
    }

    private void aumentaCapacidade() {
        if (this.tamanho == this.contas.length) {
            Object[] clientesNovos = new Object[this.contas.length + 10];
            for (int i = 0; i < this.contas.length; i++) {
                clientesNovos[i] = this.contas[i];
            }
        }
    }

    public void excluirConta(int numeroConta) throws Exception {
        buscarConta(numeroConta);
        int posicao = this.posicao;
        
        if (posicao >= 0 && posicao < tamanho) {
            for (int i = posicao; i < this.tamanho - 1; i++) {
                this.contas[i] = this.contas[i + 1];
            }
            this.tamanho--;
        } else {
            throw new Exception("Conta inválida!");
        }
    }

    public Contas buscarConta(int numeroConta) throws Exception {
        for (int i = 0; i < tamanho; i++) {
            if (contas[i] != null && contas[i].getConta() == numeroConta) {
                this.posicao = i;
                return contas[i];
            }
        }
        return null;
    }

    public void deposito(int numeroConta, double valorDeposito) throws Exception {
        Contas conta = buscarConta(numeroConta);
        if (conta != null) {
            double saldo = conta.getSaldo();
            saldo += valorDeposito;
            conta.setSaldo(saldo);
        }else {
            throw new Exception("Conta não encontrada");
        }
    }

    public void transferencia(int contaPrincipal, int contaDestino, double valorTransferencia) throws Exception {
        Contas conta1 = buscarConta(contaPrincipal);
        Contas conta2 = buscarConta(contaDestino);
    
        if (conta1 != null && conta2 != null) {
            double saldoConta1 = conta1.getSaldo();
            // double saldoConta2 = conta2.getSaldo();
    
            if (saldoConta1 >= valorTransferencia) {
                saldoConta1 -= valorTransferencia;
                conta1.setSaldo(saldoConta1);
                double saldo = conta2.getSaldo();
                saldo += valorTransferencia;
                conta2.setSaldo(saldo);
                
            } else {
                throw new Exception("Saldo Insuficiente!");
            }
        } else {
            throw new Exception("Conta não encontrada");
        }
    }
    

}