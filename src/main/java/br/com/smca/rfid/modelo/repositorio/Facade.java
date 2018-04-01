/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Gleywson
 * @param <T>
 */
public abstract class Facade<T> implements Dao<T>, Serializable {
    private Class entity;

    public Facade(Class entity) {
        this.entity = entity;
    }
    
    protected abstract EntityManager getEntityManager();

    @Override
    public void salvar(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void alterar(T t) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(t);
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }

    @Override
    public void excluir(T t) {
        getEntityManager().getTransaction().begin();
        t = getEntityManager().merge(t);
        getEntityManager().remove(t);
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }

    @Override
    public List<T> listarTudo() {
        CriteriaQuery query = getEntityManager().getCriteriaBuilder().createQuery();
        query.select(query.from(entity));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    

    
    
    
    
}
