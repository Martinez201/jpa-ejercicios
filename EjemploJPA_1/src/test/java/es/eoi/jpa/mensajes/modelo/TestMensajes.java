package es.eoi.jpa.mensajes.modelo;

import autor.Autor;
import mensaje.Mensaje;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class TestMensajes {

    private static EntityManagerFactory emf;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    // Se ejecuta una vez antes de todos los tests
    @BeforeClass
    public static void initDatabaseTest() {
        try {
// Inicializamos sólo una vez el emf antes de todos los tests
            emf = Persistence.createEntityManagerFactory("mensajes-mysql");
// Inicializamos la conexión a la BD necesaria para (1)
// que DBUnit cargue los datos de los tests
            Class.forName("com.mysql.jdbc.Driver");
            Connection jdbcConnection = (Connection) DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/jpa_mensajes",

                            "root", "oretania");

            connection = new DatabaseConnection(jdbcConnection);
            FlatXmlDataSetBuilder flatXmlDataSetBuilder =
                    new FlatXmlDataSetBuilder();
            flatXmlDataSetBuilder.setColumnSensing(true);
            dataset = flatXmlDataSetBuilder.build(Thread.currentThread()
                    .getContextClassLoader()

                    .getResourceAsStream("dbunit/dataset1.xml"));

        } catch (Exception ex) {

            ex.printStackTrace();
            fail("Excepción al inicializar el emf y DbUnit");
        }
    }
    // Se ejecuta antes de cada test
    @Before
    public void cleanDB() throws Exception {
// Se hace un "clean insert" de los datos de prueba
// definidos en el fichero XML. El "clean insert" vacía las
// tablas de los datos de prueba y después inserta los datos
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }
    @Test
    public void persistAñadeUnNuevoAutor() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Autor autor = new Autor("Jose Francisco", "Martínez Aguilar","jose.francisco@eoi.es","25-07-1977","900600900","Avenida de Andalucia 20 8ºD");
        em.persist(autor);
        em.getTransaction().commit();
        int id = autor.getId();
        Autor autor2 = em.find(Autor.class, id);
        assertEquals(autor2, autor);
        em.close();
    }

    @Test
    public void createEntityManagerTest() {
        EntityManager em = emf.createEntityManager();
        assertNotNull(em);
        em.close();
    }
    @Test
    public void findDevuelveAutor() {
        EntityManager em = emf.createEntityManager();
        Autor autor = em.find(Autor.class, 1);
        assertEquals("antonio.martinez@eoi.es", autor.getCorreo());
    }
    @Test
    public void findDevuelveAutorConMensajes() {
        EntityManager em = emf.createEntityManager();
        Autor autor = em.find(Autor.class, 1);
        assertEquals(2, autor.getMensajes().size());
    }
    // Se ejecuta una vez después de todos los tests
    @AfterClass
    public static void closeEntityManagerFactory() throws Exception {
// Borramos todos los datos y cerramos la conexión
        DatabaseOperation.DELETE_ALL.execute(connection, dataset);
        if (emf != null)
        emf.close();
    }

    //si se pone en modo lazy la recuperación de los datos de una coleccion , solo recupera un cierto numero de elementos
    // los datos se van recuperando a medida que se vaya recorriendo la colección, si se cierra la sesión aunque haya mas
    //objetos en la colección no se recuperan y dan error.

    @Test
    public void comprobarLazy(){

        EntityManager em = emf.createEntityManager();
        Autor autor = em.find(Autor.class, 1);


        em.close();

       try {

           Set<Mensaje> mensajes2 = autor.getMensajes();

           System.out.println(mensajes2.size());

       }catch (Exception ex){

           System.out.println("Error recuperación de datos en modo LAZY y se cerro la sessión");
           ex.printStackTrace();

       }

    }
}