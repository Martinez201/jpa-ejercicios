package main;

import DAO.PeliculaDao;
import Singleton.Singleton;
import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PruebasDao {

    public static void main(String[] args) {

        EntityManagerFactory emf = Singleton.getInstance().getEmf();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        PeliculaDao pld = new PeliculaDao(em);

        for (Pelicula pl : pld.lisAllMovies()){

            System.out.println(pl.getTitulo());
        }

        pld.delete(pld.find(1));

        System.out.println("\n\n");

        for (Pelicula pl : pld.lisAllMovies()){

            System.out.println(pl.getTitulo());
        }

        Pelicula editar = pld.find(2);

        editar.setTitulo("Moco");

        pld.update(editar);

        System.out.println("\n\n");

        for (Pelicula pl : pld.lisAllMovies()){

            System.out.println(pl.getTitulo());
        }

        em.getTransaction().commit();

        em.close();
        emf.close();

    }

}
