package com.DAO;

import com.Conexao.Conexao;
import com.DTO.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class VeiculoDAO {
	
	 public Veiculo cadastrarVeiculo(Veiculo veiculo, int usuarioId) throws SQLException {
	        String sql = "INSERT INTO veiculos (placa, modelo, tipo, usuario_id) VALUES (?, ?, ?, ?)";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            stmt.setString(1, veiculo.getPlaca());
	            stmt.setString(2, veiculo.getModelo());
	            stmt.setString(3, veiculo.getTipo());
	            stmt.setInt(4, usuarioId);
	            
	            stmt.executeUpdate();
	            
	            return veiculo;
	        }
	    }

	    public Veiculo encontrarVeiculoPorPlaca(String placa) throws SQLException {
	        String sql = "SELECT * FROM veiculos WHERE placa = ?";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, placa);
	            
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Veiculo veiculo = new Veiculo(
	                    rs.getString("placa"), 
	                    rs.getString("modelo"), 
	                    rs.getString("tipo")
	                );
	                veiculo.setHoraEntrada(rs.getTimestamp("hora_entrada") != null 
	                    ? rs.getTimestamp("hora_entrada").toLocalDateTime() 
	                    : null);
	                veiculo.setHoraSaida(rs.getTimestamp("hora_saida") != null 
	                    ? rs.getTimestamp("hora_saida").toLocalDateTime() 
	                    : null);
	                return veiculo;
	            }
	            return null;
	        }
	    }

	    public void registrarEntrada(int veiculoId, LocalDateTime horaEntrada) throws SQLException {
	        String sql = "UPDATE veiculos SET hora_entrada = ? WHERE id = ?";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setTimestamp(1, Timestamp.valueOf(horaEntrada));
	            stmt.setInt(2, veiculoId);
	            stmt.executeUpdate();
	        }
	    }

	    public void registrarSaida(int veiculoId, LocalDateTime horaSaida) throws SQLException {
	        String sql = "UPDATE veiculos SET hora_saida = ? WHERE id = ?";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setTimestamp(1, Timestamp.valueOf(horaSaida));
	            stmt.setInt(2, veiculoId);
	            stmt.executeUpdate();
	        }
	    }

}
