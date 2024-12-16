package com.DAO;

import com.Conexao.Conexao;
import com.DTO.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class VagaDAO {

	public List<Vaga> buscarVagasDisponiveis() throws SQLException {
        List<Vaga> vagasDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM vagas WHERE ocupada = FALSE";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vaga vaga = new Vaga(rs.getInt("numero"), rs.getString("tipo"));
                vagasDisponiveis.add(vaga);
            }
        }
        return vagasDisponiveis;
    }

    public void atribuirVeiculo(int vagaId, int veiculoId) throws SQLException {
        String sql = "UPDATE vagas SET ocupada = TRUE, veiculo_id = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculoId);
            stmt.setInt(2, vagaId);
            stmt.executeUpdate();
        }
    }

    public void liberarVaga(int vagaId) throws SQLException {
        String sql = "UPDATE vagas SET ocupada = FALSE, veiculo_id = NULL WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vagaId);
            stmt.executeUpdate();
        }
    }
	
}
