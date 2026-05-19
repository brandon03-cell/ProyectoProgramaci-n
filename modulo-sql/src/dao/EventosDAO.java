package dao;

import modelo.Eventos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventosDAO {
    private String url = "jdbc:mysql://localhost:3308/proyectoProgramacion";
    private String user = "root";
    private String password = "Brandon_01052003";

    public void insertarEvento(Eventos e) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "insert into eventos (nombre, ubicacion, fecha, precio) values (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUbicacion());
            ps.setString(3, e.getFecha());
            ps.setDouble(4, e.getPrecio());
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error" + exception.getMessage());
        }
    }

    public void actualizarEvento(int id) {

    }

}
