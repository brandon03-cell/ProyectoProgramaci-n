import dao.ProyectoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Proyecto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ultimate.odb");
        ProyectoDAO proyectoDAO = new ProyectoDAO(emf);

        System.out.println("Método para mostar todos los proyectos");
        List<Proyecto> obtenerProyectos = proyectoDAO.obtenerProyectos();
        for (Proyecto p : obtenerProyectos) {
            System.out.println(p);
        }
    }
}