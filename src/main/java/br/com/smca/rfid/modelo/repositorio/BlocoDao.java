/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import br.com.smca.rfid.modelo.Bloco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Gleywson
 */
public class BlocoDao {

    public void salvarAtualizar(Bloco bloco) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (bloco.getId() != null) {
            bloco = em.merge(bloco);
        }
        em.persist(bloco);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Bloco bloco) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        bloco = em.merge(bloco);

        em.remove(bloco);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Bloco> pesquisar(Bloco bloco) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Bloco b "
                + "where 1 = 1 ");
        if(bloco.getId() != null) {
            sql.append("and b.id = :id ");
        }
        if(bloco.getNome() != null && !bloco.getNome().equals("")) {
            sql.append("and b.nome like :nome ");
        }
        Query query = em.createQuery(sql.toString());
        
        if(bloco.getId() != null) {
            query.setParameter("id", bloco.getId());
        }
        
        if(bloco.getNome() != null && !bloco.getNome().equals("")) {
            query.setParameter("nome", "%"+bloco.getNome()+"%");
        }
        return query.getResultList();
    }
}
