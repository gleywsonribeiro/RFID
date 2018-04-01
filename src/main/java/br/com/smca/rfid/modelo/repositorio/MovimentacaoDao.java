/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import br.com.smca.rfid.modelo.Movimentacao;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Gleywson
 */
public class MovimentacaoDao extends Facade<Movimentacao> {

    private final EntityManager manager = Conexao.getEntityManager();
    
    public MovimentacaoDao() {
        super(Movimentacao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }

    @Override
    public List<Movimentacao> pesquisar(Movimentacao t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
