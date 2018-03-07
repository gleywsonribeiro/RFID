/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import br.com.smca.rfid.modelo.Bloco;
import java.util.List;

/**
 *
 * @author Gleywson
 */
public interface BlocoDao {

    void excluir(Bloco bloco);

    List<Bloco> pesquisar(Bloco bloco);

    void salvarAtualizar(Bloco bloco);
    
}
