import dao.ProyectoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Proyecto;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ultimate.odb");
        ProyectoDAO proyectoDAO = new ProyectoDAO(emf);

        proyectoDAO.insertarProyecto(new Proyecto("Sistema ERP", 50000.0, "Java"));
        proyectoDAO.insertarProyecto(new Proyecto("App Delivery", 12000.0, "Kotlin"));
        proyectoDAO.insertarProyecto(new Proyecto("Portal E-commerce", 25000.0, "JavaScript"));
        proyectoDAO.insertarProyecto(new Proyecto("Motor IA Clasificador", 80000.0, "Python"));
        proyectoDAO.insertarProyecto(new Proyecto("Dashboard Cripto", 15000.0, "TypeScript"));
        proyectoDAO.insertarProyecto(new Proyecto("Gestor Hospitalario", 45000.0, "Java"));
        proyectoDAO.insertarProyecto(new Proyecto("Red Social Gaming", 30000.0, "C#"));
        proyectoDAO.insertarProyecto(new Proyecto("App Finanzas Personales", 8000.0, "Swift"));
        proyectoDAO.insertarProyecto(new Proyecto("Sistema Logística", 60000.0, "Go"));
        proyectoDAO.insertarProyecto(new Proyecto("Analítica Big Data", 95000.0, "Python"));

    }
}