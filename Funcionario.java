package projetofinal;

import java.time.LocalDateTime;


class Funcionario extends Usuario {
    protected boolean isAdmin;

    public Funcionario() {
        this.isAdmin = false;
    }

    public void setAdminStatus(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Veiculo cadastrarVeiculo(String placa, String modelo, String tipo) {
        Veiculo veiculo = new Veiculo(placa, modelo, tipo);
        System.out.println("Veículo cadastrado: " + placa);
        return veiculo;
    }
    
    public void registrarEntrada(Veiculo veiculo) {
        veiculo.setHoraEntrada(LocalDateTime.now());
        System.out.println("Entrada registrada para o veículo: " + veiculo.getPlaca());
    }
    
    public void registrarSaida(Veiculo veiculo, Estacionamento estacionamento) {
        veiculo.setHoraSaida(LocalDateTime.now());
        
        // Find and free the vaga for this vehicle
        for (Vaga vaga : estacionamento.getVagas()) {
            if (vaga.getVeiculo() == veiculo) {
                vaga.liberarVaga();
                break;
            }
        }
        
        System.out.println("Saída registrada para o veículo: " + veiculo.getPlaca());
    }

    // Métodos de admin, disponíveis apenas se isAdmin for true
    
    public void modificarTarifa(Tarifa tarifa, double novoValor) {
        if (isAdmin) {
            tarifa.setValorPorHora(novoValor);
            System.out.println("Tarifa atualizada para R$" + novoValor + " por hora.");
        } else {
            System.out.println("Acesso negado. Apenas administradores podem modificar tarifas.");
        }
    }
    
    public void gerarRelatorioFaturamento(Estacionamento estacionamento) {
        if (isAdmin) {
            Relatorio relatorio = new Relatorio();
            relatorio.relatorioFaturamento(estacionamento);
        } else {
            System.out.println("Acesso negado. Apenas administradores podem gerar relatórios de faturamento.");
        }
    }
    
    public void gerarRelatorioFluxo(Estacionamento estacionamento) {
        if (isAdmin) {
            Relatorio relatorio = new Relatorio();
            relatorio.relatorioFluxo(estacionamento);
        } else {
            System.out.println("Acesso negado. Apenas administradores podem gerar relatórios de fluxo.");
        }
    }

    // Métodos de cliente para admin
    public void verDetalhesDaEstadia(Veiculo veiculo) {
        if (isAdmin) {
            System.out.println("Detalhes da Estadia:");
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Tipo: " + veiculo.getTipo());
            System.out.println("Tempo de Permanência: " + veiculo.calcularTempoPermanencia() + " horas");
        } else {
            System.out.println("Acesso negado. Apenas administradores podem ver todos os detalhes.");
        }
    }
    
    public Pagamento realizarPagamento(Veiculo veiculo, Tarifa tarifa) {
        if (isAdmin) {
            double valorPagamento = veiculo.calcularTarifa(tarifa);
            Pagamento pagamento = new Pagamento(valorPagamento, veiculo);
            pagamento.registrarPagamento();
            return pagamento;
        } else {
            System.out.println("Acesso negado. Apenas administradores podem realizar pagamentos para outros.");
            return null;
        }
    }
    
    public void visualizarRecibos(Estacionamento estacionamento) {
        if (isAdmin) {
            System.out.println("--- Todos os Recibos ---");
            for (Usuario usuario : estacionamento.getUsuarios()) {
                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;
                    cliente.imprimirRecibos();
                }
            }
        } else {
            System.out.println("Acesso negado. Apenas administradores podem visualizar todos os recibos.");
        }
    }
    
    public void visualizarRecibo(Pagamento pagamento) {
        if (isAdmin) {
            System.out.println(pagamento.emitirRecibo());
        } else {
            System.out.println("Acesso negado. Apenas administradores podem visualizar recibos.");
        }
    }
    
}