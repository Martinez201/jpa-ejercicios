package main;

import Singleton.Singleton;
import critica.Critica;
import pelicula.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevaCritica {

    public static void main(String[] args) {


        EntityManagerFactory emf = Singleton.getInstance().getEmf();

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Pelicula pelicula;

        try {

            int idPeli = Integer.parseInt(leerTexto("Inserte id Pelicula ==> "));

            pelicula = em.find(Pelicula.class,idPeli);

            if (pelicula == null){

                System.out.println("Pelicula no existente");

            }else {

                System.out.println("Pelicula " + pelicula.getTitulo());

                String critico = leerTexto("\nIntroduzca nombre critico ==> ");
                String critica = leerTexto("\nIntroduzca texto ==> ");
                int valoracion = Integer.parseInt(leerTexto("\nIntroduzca calificacion ==> ")) ;

                Critica ct = new Critica(critico,critica,valoracion);

               ct.setPelicula(pelicula);

                em.persist(ct);

                System.out.println("Identificador de la critica: " + ct.getId());

            }


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
