import dao.DesarrolladorDAO;
import dao.ProyectoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ultimate.odb");
        ProyectoDAO proyectoDAO = new ProyectoDAO(emf);
        DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(emf);

        System.out.println("Consultas\n");
        System.out.println("Obtener el número de desarrolladores de cada proyecto");
        Map<String, Long> numDesarrolladores = proyectoDAO.desarrolladoresPorProyecto();
        for (Map.Entry<String, Long> entrada : numDesarrolladores.entrySet()) {
            System.out.println("Proyecto: " + entrada.getKey() + " - Desarrolladores: " + entrada.getValue());
        }

        System.out.println("\nObtener todos los Desarrollador que pertenecen a un proyecto concreto, según su ID");
        List<Desarrollador> devsProyecto10 = proyectoDAO.obtenerDesarrolladoresDeProyecto(10);
        for (Desarrollador d : devsProyecto10) {
            System.out.println(d.toString());
        }

        System.out.println("\nObtener los Proyecto que tengan más de 5 desarrolladores");
        List<Proyecto> proyectosMas5 = proyectoDAO.obtenerProyectosMasDe5Desarrolladores();
        for (Proyecto p : proyectosMas5) {
            System.out.println(p.toString());
        }

        System.out.println("\nObtener los 3 Proyecto con el presupuesto más alto");
        List<Proyecto> top3Proyectos = proyectoDAO.obtenerTresProyectosMasPresupuesto();
        for (Proyecto p : top3Proyectos) {
            System.out.println(p.toString());
        }

        System.out.println("\nObtener el Proyecto con un lenguajePrincipal, dado por parámetro, con el presupuesto más bajo");
        Proyecto proyBajoJava = proyectoDAO.obtenerProyectoPresupuestoMasBajoPorLenguaje("Java");
        if (proyBajoJava != null) {
            System.out.println(proyBajoJava.toString());
        }

        System.out.println("\nObtener todos los Proyecto de un desarrollador, según su ID");
        List<Proyecto> proyectosDev11 = desarrolladorDAO.obtenerProyectosDeDesarrollador(11);
        for (Proyecto p : proyectosDev11) {
            System.out.println(p.toString());
        }

        System.out.println("\nObtener la media de años de experiencia de todos los desarrolladores");
        System.out.println(desarrolladorDAO.obtenerMediaAnyosExperiencia() + " años.");

        System.out.println("\nObtener los Desarrollador que no tienen ningún proyecto asignado");
        List<Desarrollador> devsSinProyectos = desarrolladorDAO.obtenerDesarrolladoresSinProyectos();
        if (devsSinProyectos.isEmpty()) {
            System.out.println("Todos los desarrolladores tienen al menos un proyecto.");
        } else {
            for (Desarrollador d : devsSinProyectos) {
                System.out.println(d.toString());
            }
        }

        System.out.println("\nAñadir -> Actualizar -> Asignar -> Eliminar");

        Proyecto pTest = new Proyecto("Proyecto Franco", 15000.0, "Java");
        Desarrollador dTest = new Desarrollador("Terry Chavez", 90, 1.9);
        proyectoDAO.insertarProyecto(pTest);
        desarrolladorDAO.insertarDesarrollador(dTest);
        int idProy = pTest.getId();
        int idDev = dTest.getId();
        proyectoDAO.actualizarProyecto(idProy, "Proyecto Franco V2", 18000.0, "Go");
        desarrolladorDAO.actualizarDesarrollador(idDev, "Terry Pavón", 3, 28.0);
        desarrolladorDAO.asignarDesarrollador(idDev, idProy);
        desarrolladorDAO.eliminarAsignacion(idDev, idProy);
        desarrolladorDAO.borrarDesarrollador(idDev);
        proyectoDAO.borrarProyecto(idProy);

        emf.close();
    }
}