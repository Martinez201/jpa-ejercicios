package DAO;

import critica.Critica;
import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class CriticaDao extends Dao<Pelicula, Integer> {

    Set<Critica> criticas = null;
    String FIND_ALL_CRITICAS_MOVIE = "SELECT c FROM Critica c WHERE c.pelicula = :pelicula";

    public CriticaDao(EntityManager em){

        super(em);

    }

    @Override
    public Pelicula find(Integer id) {
        EntityManager em = this.getEm();

        Pelicula pl = em.find(Pelicula.class , id);

        return pl;
    }

    public List<Critica> listAllCriticasMovie(Integer id){

        EntityManager em = this.getEm();
        Query query = em.createQuery(FIND_ALL_CRITICAS_MOVIE).setParameter("pelicula",id);
        return  (List<Critica>) query.getResultList();
    }

}
