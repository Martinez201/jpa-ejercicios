package main;

import Singleton.Singleton;
import servicio.PeliculaServicio;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;

public class PruebaServicios {

    public static void main(String[] args) {

        PeliculaServicio ps = new PeliculaServicio();

        EntityManagerFactory emf = Singleton.getInstance().getEmf();

        ps.setEmf(emf);

       try {

          // ps.creaPelicula("El padrino",new SimpleDateFormat("dd-MM-yyyy").parse("23-02-1972"),"Estados Unidos",78955.74);

           ps.actualizaRecaudacion(3,78884445.21);
       }catch (Exception ex){

           ex.printStackTrace();

       }
       finally {

           emf.close();
       }

    }

}
