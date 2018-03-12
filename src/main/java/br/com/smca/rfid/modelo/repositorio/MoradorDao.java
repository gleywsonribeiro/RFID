/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import br.com.smca.rfid.modelo.Morador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Gleywson
 */
public class MoradorDao {

    public void salvarAtualizar(Morador morador) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (morador.getId() != null) {
            morador = em.merge(morador);
        }
        em.persist(morador);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(Morador morador) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        morador = em.merge(morador);

        em.remove(morador);
        em.getTransaction().commit();
        em.close();
    }

    public List<Morador> pesquisar(Morador morador) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Morador m "
                + "where 1 = 1 ");
        if (morador.getId() != null) {
            sql.append("and m.id = :id ");
        }
        if (morador.getNome() != null && !morador.getNome().equals("")) {
            sql.append("and m.nome like :nome ");
        }
        Query query = em.createQuery(sql.toString());

        if (morador.getId() != null) {
            query.setParameter("id", morador.getId());
        }

        if (morador.getNome() != null && !morador.getNome().equals("")) {
            query.setParameter("nome", "%" + morador.getNome() + "%");
        }
        return query.getResultList();
    }
}
