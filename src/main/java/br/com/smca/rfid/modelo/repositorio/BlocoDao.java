/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import br.com.smca.rfid.modelo.Bloco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Gleywson
 */
public class BlocoDao {

    private static BlocoDao instance;
    protected EntityManager entityManager;

    public static BlocoDao getInstance() {
        if (instance == null) {
            instance = new BlocoDao();
        }

        return instance;
    }

    private BlocoDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("RFIDPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Bloco getById(final int id) {
        return entityManager.find(Bloco.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Bloco> findAll() {
        return entityManager.createQuery("FROM " + Bloco.class.getName()).getResultList();
    }

    public void persist(Bloco bloco) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bloco);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro salvar o registro. Entrar em contato com o suporte!");
        }
    }

    public void merge(Bloco bloco) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bloco);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Bloco bloco) {
        try {
            entityManager.getTransaction().begin();
            bloco = entityManager.find(Bloco.class, bloco.getId());
            entityManager.remove(bloco);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int id) {
        try {
            Bloco bloco = getById(id);
            remove(bloco);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
