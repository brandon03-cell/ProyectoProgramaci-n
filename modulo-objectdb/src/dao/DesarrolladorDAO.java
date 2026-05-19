package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.List;

public class DesarrolladorDAO {
    private EntityManagerFactory emf;

    public DesarrolladorDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertarDesarrollador(Desarrollador desarrollador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(desarrollador);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarDesarrollador(int id, String nombre, int anyosExperiencia, double salario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, id);
        if (d != null) {
            d.setNombre(nombre);
            d.setAnyosExperiencia(anyosExperiencia);
            d.setSalario(salario);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, id);
        if (d != null) {
            em.remove(d);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void asignarDesarrollador(int idDesarrollador, int idProyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, idDesarrollador);
        Proyecto p = em.find(Proyecto.class, idProyecto);
        if (d != null && p != null) {
            p.getDesarrolladores().add(d);
            d.getProyectos().add(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void eliminarAsignacion(int idDesarrollador, int idProyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, idDesarrollador);
        Proyecto p = em.find(Proyecto.class, idProyecto);
        if (d != null && p != null) {
            p.getDesarrolladores().remove(d);
            d.getProyectos().remove(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Proyecto> obtenerProyectosDeDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery("select p from Proyecto p join p.desarrolladores d where d.id = :id", Proyecto.class);
        query.setParameter("id", id);
        List<Proyecto> resultado = query.getResultList();
        em.close();
        return resultado;
    }

    public double obtenerMediaAnyosExperiencia() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> query = em.createQuery("select avg(d.anyosExperiencia) from Desarrollador d", Double.class);
        Double media = query.getSingleResult();
        em.close();
        return media != null ? media : 0.0;
    }

    public List<Desarrollador> obtenerDesarrolladoresSinProyectos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Desarrollador> query = em.createQuery("select d from Desarrollador d left join d.proyectos p where p is null", Desarrollador.class);
        List<Desarrollador> resultado = query.getResultList();
        em.close();
        return resultado;
    }

    public List<Desarrollador> NoFuncionaobtenerDesarrolladoresSinProyectos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Desarrollador> query = em.createQuery("select d from Desarrollador d where size(d.proyectos) = 0", Desarrollador.class);
        List<Desarrollador> resultado = query.getResultList();
        em.close();
        return resultado;
    }
}