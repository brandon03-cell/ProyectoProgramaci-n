package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import modelo.Proyecto;

public class ProyectoDAO {
    private EntityManagerFactory emf;

    public void insertarProyecto(Proyecto proyecto) {
        EntityManager em = emf.createEntityManager();
    }
}
