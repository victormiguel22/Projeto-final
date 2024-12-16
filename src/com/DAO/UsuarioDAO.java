package com.DAO;

import com.Conexao.Conexao;
import com.DTO.*;
import java.sql.*;

public class UsuarioDAO {
    public Usuario autenticar(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo_usuario");
                
                switch (tipo) {
                    case "ADMIN":
                    case "FUNCIONARIO":
                        Funcionario funcionario = new Funcionario();
                        funcionario.setNome(rs.getString("nome"));
                        funcionario.setLogin(rs.getString("login"));
                        funcionario.setSenha(rs.getString("senha"));
                        funcionario.setAdminStatus(tipo.equals("ADMIN"));
                        return funcionario;
                    case "CLIENTE":
                        Cliente cliente = new Cliente();
                        cliente.setNome(rs.getString("nome"));
                        cliente.setLogin(rs.getString("login"));
                        cliente.setSenha(rs.getString("senha"));
                        return cliente;
                    default:
                        return null;
                }
            }
            return null;
        }
    }

    public boolean registrarUsuario(Usuario usuario, boolean isAdmin) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, login, senha, tipo_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            
            if (usuario instanceof Funcionario && isAdmin) {
                stmt.setString(4, "ADMIN");
            } else if (usuario instanceof Funcionario) {
                stmt.setString(4, "FUNCIONARIO");
            } else if (usuario instanceof Cliente) {
                stmt.setString(4, "CLIENTE");
            } else {
                return false;
            }
            
            stmt.executeUpdate();
            return true;
        }
    }
}