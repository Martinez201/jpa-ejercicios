package DAO;

import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PeliculaDao extends Dao <Pelicula,Integer>{

    String FIND_ALL_MOVIES = "SELECT p FROM Pelicula p";

    public PeliculaDao(EntityManager em) {

        super(em);

    }

    @Override
    public Pelicula find(Integer id) {

        EntityManager em = this.getEm();
        return em.find(Pelicula.class, id);

    }

    public List<Pelicula> lisAllMovies(){

        EntityManager em = this.getEm();
        Query query = em.createQuery(FIND_ALL_MOVIES);
        return (List<Pelicula>) query.getResultList();
    }

}
