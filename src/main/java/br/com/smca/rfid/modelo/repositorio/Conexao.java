/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gleywson
 */
public class Conexao {
    private static EntityManagerFactory emf;
    private static Conexao conexao;

    private Conexao() {
        emf = Persistence.createEntityManagerFactory("RFIDPU");
    }
    
    public static synchronized EntityManager getEntityManager() {
        if(conexao == null) {
            conexao = new Conexao();
        }
        return emf.createEntityManager();
    }
}
