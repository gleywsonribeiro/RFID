/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import java.util.List;

/**
 *
 * @author Gleywson
 * @param <T>
 */
public interface Dao<T> {
    public void salvar(T t);
    public void alterar(T t);
    public void excluir(T t);
    public List<T> pesquisar(T t);
    public List<T> listarTudo();
    
}
