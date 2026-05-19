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

    public void actualizarEvento(Eventos e) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "update eventos set nombre = ?, ubicacion = ?, fecha = ?, precio = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getUbicacion());
            ps.setString(3, e.getFecha());
            ps.setDouble(4, e.getPrecio());
            ps.setInt(5, e.getId());
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public void borrarEvento(int id) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sqlInscripción = "delete from inscripcion where evento_id = ?";
            PreparedStatement ps1 = con.prepareStatement(sqlInscripción);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            String sqlEvento = "delete from eventos where id = ?";
            PreparedStatement ps2 = con.prepareStatement(sqlEvento);
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error: " + exception.getMessage());
        }

    }


}
