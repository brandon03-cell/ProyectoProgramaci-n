package dao;

import modelo.Asistente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AsistenteDAO {
    private String url = "jdbc:mysql://localhost:3308/proyectoProgramacion";
    private String user = "root";
    private String password = "Brandon_01052003";

    public void insertarAsistente(Asistente a) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "insert into asistentes (nombre, email, edad) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getEdad());
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Erro ao inserir asistente: " + exception.getMessage());
        }
    }

    public void actualizarAsistente(Asistente a) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "update asistentes set nombre = ?, email = ?, edad = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEmail());
            ps.setInt(3, a.getEdad());
            ps.setInt(4, a.getId());
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Erro ao actualizar asistente: " + exception.getMessage());
        }
    }
}
