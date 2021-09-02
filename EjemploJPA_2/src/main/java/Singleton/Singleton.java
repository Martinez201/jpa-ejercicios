package Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {

    private static Singleton instancia = new Singleton();
    protected static final String PERSISTENCE_UNIT_NAME = "filmoteca-mysql";
    private EntityManagerFactory emf = null;

    public static Singleton getInstance(){

        return  instancia;

    }

    private Singleton(){

    }

    public EntityManagerFactory getEmf(){

        if (this.emf == null){

            this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }

        return this.emf;
    }

}
