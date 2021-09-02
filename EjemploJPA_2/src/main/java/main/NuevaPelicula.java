package main;

import Singleton.Singleton;
import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import java.util.Date;

public class NuevaPelicula {

    public static void main(String[] args) {

        EntityManagerFactory emf = Singleton.getInstance().getEmf();

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Pelicula pelicula;

        try {

            String titulo = leerTexto("Inserte titulo ==> ");

            Date estreno = new SimpleDateFormat("dd-MM-yyyy").parse(leerTexto("Inserte una fecha de estreno ==> "));

            double presupuesto = Double.parseDouble(leerTexto("Inserte presupuesto ==> "));

            double recaudacion = Double.parseDouble(leerTexto("Inserte recaudación ==> "));

            String pais = leerTexto("Inserte pais ==> ") ;

            pelicula = new Pelicula(titulo,estreno,presupuesto,recaudacion,pais);

            em.persist(pelicula);

            System.out.println("Identificador de la pelicula: " + pelicula.getId());
            int idAutor = pelicula.getId();


            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace();

        }finally {

            em.close();
            emf.close();
        }

    }


    static private String leerTexto(String mensaje) {

        String texto;

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            texto = in.readLine();

        } catch (IOException e) {

            texto = "Error";

        }

        return texto;
    }
}