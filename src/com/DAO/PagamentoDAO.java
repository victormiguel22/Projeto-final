package com.DAO;

import com.Conexao.Conexao;
import com.DTO.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class PagamentoDAO {

	public Pagamento registrarPagamento(Pagamento pagamento, int veiculoId) throws SQLException {
        String sql = "INSERT INTO pagamentos (valor, data, status, veiculo_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, pagamento.getValor());
            stmt.setTimestamp(2, Timestamp.valueOf(pagamento.getData()));
            stmt.setString(3, pagamento.getStatus());
            stmt.setInt(4, veiculoId);
            
            stmt.executeUpdate();
            
            return pagamento;
        }
    }

    public List<Pagamento> buscarPagamentosPorCliente(int clienteId) throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT p.* FROM pagamentos p " +
                     "JOIN veiculos v ON p.veiculo_id = v.id " +
                     "WHERE v.usuario_id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new VeiculoDAO().encontrarVeiculoPorPlaca(
                    rs.getString("veiculo_placa")
                );
                
                Pagamento pagamento = new Pagamento(
                    rs.getDouble("valor"),
                    veiculo
                );
                pagamento.setData(rs.getTimestamp("data").toLocalDateTime());
                pagamento.setStatus(rs.getString("status"));
                
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }

}
