package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Proyecto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectoDAO {
    private EntityManagerFactory emf;

    public ProyectoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

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
        TypedQuery<Object[]> query = em.createQuery("select p.nombre, count(d) from Proyecto p left join p.desarrolladores d group by p.nombre", Object[].class);
        List<Object[]> resultado = query.getResultList();
        Map<String, Long> mapa = new HashMap<>();
        for (Object[] fila : resultado) {
            mapa.put((String) fila [0], (Long) fila [1]);
        }
        em.close();
        return mapa;
    }

    public List<Proyecto> obtenerProyectos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery("select p from Proyecto p", Proyecto.class);
        List<Proyecto> resultado = query.getResultList();
        em.close();
        return resultado;
    }
}
