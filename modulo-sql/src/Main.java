import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3308/proyectoProgramacion";
        String user = "root";
        String password = "Brandon_01052003";

        /*
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
         */
    }
}