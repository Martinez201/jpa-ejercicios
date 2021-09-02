package DAO;

import javax.persistence.EntityManager;

abstract class Dao <T,K> {

   private EntityManager em;

    public Dao(EntityManager em){

        this.em = em;

    }


    public EntityManager getEm() {

        return this.em;

    }

    public abstract T find(K id);

    public T create(T t){

        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

    public T update(T t){

        return (T) em.merge(t);

    }

    public void delete(T t){

        t = em.merge(t);
        em.remove(t);

    }
}
