package com.DAO;
import com.Conexao.Conexao;
import com.DTO.*;
import java.sql.*;

public class TarifaDAO {

	public Tarifa buscarTarifaAtual() throws SQLException {
        String sql = "SELECT * FROM tarifas ORDER BY id DESC LIMIT 1";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Tarifa(
                    rs.getDouble("valor_por_hora"),
                    rs.getDouble("fracao_minima_hora")
                );
            }
            return null;
        }
    }

    public void atualizarTarifa(double valorPorHora, double fracaoMinimaHora) throws SQLException {
        String sql = "INSERT INTO tarifas (valor_por_hora, fracao_minima_hora) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valorPorHora);
            stmt.setDouble(2, fracaoMinimaHora);
            stmt.executeUpdate();
        }
    }
	
}
