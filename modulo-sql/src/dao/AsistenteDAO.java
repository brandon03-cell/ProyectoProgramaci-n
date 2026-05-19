package dao;

import modelo.Asistente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void borrarAsistente(Asistente a) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sqlinscripciones = "delete from inscripciones where asistente_id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlinscripciones);
            ps.setInt(1, a.getId());
            ps.executeUpdate();

            String sqlasistente = "delete from asistentes where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlasistente);
            ps2.setInt(1, a.getId());
            ps2.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Erro ao borrar asistente: " + exception.getMessage());
        }
    }

    public void InscribirAsistnteAUnEventooo(int asistente_id, int evento_id, String inscripcion) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "insert into inscripciones (asistente_id, evento_id, fecha_inscripcion) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, asistente_id);
            ps.setInt(2, evento_id);
            ps.setString(3, inscripcion);
            ps.executeUpdate();
        }catch (SQLException exception){
            System.out.println("Erro ao inserir asistente: " + exception.getMessage());
        }
    }

    public void eliminarInscripcionDeAsistente(int asistente_id, int evento_id) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "delete from inscripciones where asistente_id = ? and evento_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, asistente_id);
            ps.setInt(2, evento_id);
            ps.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Erro ao eliminar asistente: " + exception.getMessage());
        }
    }

    public List<String> obtenerTodosAsistentesConSuGastoTotal() {
        List<String> resultado = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select a.nombre, sum(e.precio) as gasto_total from asistentes a join inscripciones i on a.id = i.asistente_id join eventos e on i.evento_id = e.id group by a.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getString("nombre") + " - " + rs.getDouble("gasto_total") + "€");
            }
        } catch (SQLException exception) {
            //Esta vez no me funciono el autocompletado para esta parte, por eso no está en portugues,espero que eso no me baje puntos🙏🥀
            System.out.println("Error " + exception.getMessage());
        }
        return resultado;
    }
}
