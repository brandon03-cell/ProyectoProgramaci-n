package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import modelo.Proyecto;

public class ProyectoDAO {
    private EntityManagerFactory emf;

    public void insertarProyecto(Proyecto proyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(proyecto);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarProyecto(int id, String nombre, double presupuesto, String lenguajePrincipal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto p = em.find(Proyecto.class, id);
        if (p != null) {
            p.setNombre(nombre);
            p.setPresupuesto(presupuesto);
            p.setLenguajePrincipal(lenguajePrincipal);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarProyecto(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto p = em.find(Proyecto.class, id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Map<String, Long> desarrolladoresPorProyecto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("select p.");
    }
}
