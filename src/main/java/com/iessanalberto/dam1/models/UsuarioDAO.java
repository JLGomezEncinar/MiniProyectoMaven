package com.iessanalberto.dam1.models;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {


public void createTable() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "nombre VARCHAR(100) NOT NULL, " +
            "email VARCHAR(100) NOT NULL UNIQUE, " +
            "fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
    try (Connection connection = ConexionDB.getConnection(); Statement stmt = connection.createStatement()) {
        stmt.execute(sql);
    }
}

    public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        try (Connection connection = ConexionDB.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.executeUpdate();
        }
    }

    public List<Usuario> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = ConexionDB.getConnection(); Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
                usuarios.add(new Usuario(id, nombre, email, fechaCreacion));
            }
        }
        return usuarios;
    }
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (Connection connection = ConexionDB.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setInt(3, usuario.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection connection = ConexionDB.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void limpiarTabla() throws SQLException {
        String sql = "DELETE FROM usuarios";
        try (Connection connection = ConexionDB.getConnection(); Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}


