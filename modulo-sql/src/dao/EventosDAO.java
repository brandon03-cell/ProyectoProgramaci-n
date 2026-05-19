package dao;

import modelo.Asistente;
import modelo.Eventos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> obtenerEventosNumTotalAsistentes() {
        List<String> resultado = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select e.nombre, count(i.asistente_id) as num from eventos e left join inscripciones i on e.id = i.evento_id group by e.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getString("nombre") + " - " + rs.getInt("num"));
            }
        } catch (SQLException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        return resultado;
    }

    public List<Asistente> obtenerAsistnetesDeEvento(int id) {
        List<Asistente> resultado = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "select a.* from asistentes a join inscripciones i on a.id = i.asistente_id  where i.evento_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(new Asistente(rs.getInt("id"),rs.getString("nombre"), rs.getString("email"), rs.getInt("edad")));
            }
        } catch (SQLException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        return resultado;
    }
}
