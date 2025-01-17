package Main;

import autor.Autor;
import mensaje.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MensajesAutor {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes-mysql");

        EntityManager em = emf.createEntityManager();

        try {

            System.out.println("--Buscando mensajes de autor");

            Integer idAutor = Integer.valueOf(leerTexto("Introduce identificador de autor: "));

            Autor autor = em.find(Autor.class, idAutor);

            if (autor == null) {

                System.out.println("No existe ese autor");

            } else {
                for (Mensaje mensaje : autor.getMensajes()) {

                    System.out.println(mensaje.toString());

                }
            }

        }catch (Exception ex){

            ex.printStackTrace();

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
