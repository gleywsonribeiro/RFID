/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.modelo.service;

import br.com.smca.rfid.modelo.repositorio.BlocoDao;
import br.com.smca.rfid.modelo.repositorio.BlocoDaoImpl;

/**
 *
 * @author Gleywson
 */
public class ServiceLocator {

    public static BlocoDao getBlocoDao() {
        return new BlocoDaoImpl();
    }
    
}
