package Main;

import autor.Autor;
import mensaje.Mensaje;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

public class NuevoAutorMensaje {

    public static void main(String[] args) {

        Autor autor;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes-mysql");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        try{

            String correo = leerTexto("\nInserte el correo electrónico ==> ");


            String nombre =  leerTexto("\nInserte el nombre ==> ");

            String apellidos = leerTexto("\nInserte el apellidos ==> ");

            String edad = leerTexto("\nInserte edad ==> ");

            String direccion = leerTexto("\nInserte la dirección ==> ");

            String telefono = leerTexto("\nInserte el teléfono ==> ");


            autor = new Autor(nombre,apellidos,correo,edad,telefono,direccion);

            em.persist(autor);

            System.out.println("Identificador del autor: " + autor.getId());
            int idAutor = autor.getId();


            String mensajeStr = leerTexto("Introduce mensaje: ");
            Mensaje mens = new Mensaje(mensajeStr, autor);

            mens.setFecha(new Date());

            em.persist(mens);
            int idMensaje = mens.getId();
            System.out.println("Identificador del mensaje: " + idMensaje);

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
