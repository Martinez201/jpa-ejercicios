package servicio;

import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

public class PeliculaServicio {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void setEmf(EntityManagerFactory emf){

        this.emf = emf;
        this.em = emf.createEntityManager();
    }



    public int creaPelicula(String titulo, Date fechaEstreno, String pais, double presupuesto){

        em.getTransaction().begin();
        int retorno = -1;

        try {

            Pelicula pl = new Pelicula(titulo,fechaEstreno,presupuesto,0,pais);

            em.persist(pl);

            em.getTransaction().commit();

            retorno = pl.getId();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace();
        }
        finally {

            em.close();
        }

        return retorno;
    }


    public void actualizaRecaudacion(int pelicula,double recaudado){

        em.getTransaction().begin();

        try {

            Pelicula pl = em.find(Pelicula.class,pelicula);

            pl.setRecaudacion(recaudado);

            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace();

        }finally {

            em.close();
        }

    }
}
