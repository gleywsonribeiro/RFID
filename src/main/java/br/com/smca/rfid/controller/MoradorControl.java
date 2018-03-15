/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.controller;

import br.com.smca.rfid.modelo.Bloco;
import br.com.smca.rfid.modelo.Morador;
import br.com.smca.rfid.modelo.repositorio.BlocoDao;
import br.com.smca.rfid.modelo.repositorio.MoradorDao;
import br.com.smca.rfid.modelo.service.ServiceLocator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Gleywson
 */
public class MoradorControl {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private Morador moradorDigitado;
    private Morador moradorSelecionado;
    private Morador moradorPesquisado;

    private List<Morador> moradoresTabela;
    
    private final MoradorDao moradorDao;

    

    public MoradorControl() {
        this.moradorDao = new MoradorDao();
        moradoresTabela = ObservableCollections.observableList(new ArrayList<>());
        
        novo();
        pesquisar();
    }

    public void novo() {
        setMoradorDigitado(new Morador());
        setMoradorPesquisado(new Morador());
    }

    public void pesquisar() {
        moradoresTabela.clear();
        moradoresTabela.addAll(moradorDao.pesquisar(moradorPesquisado));
    }

    public Morador getMoradorDigitado() {
        return moradorDigitado;
    }

    
    public void setMoradorDigitado(Morador moradorDigitado) {
        Morador oldMoradorDigitado = this.moradorDigitado;
        this.moradorDigitado = moradorDigitado;
        changeSupport.firePropertyChange("moradorDigitado", oldMoradorDigitado, moradorDigitado);
    }

    public Morador getMoradorPesquisado() {
        return moradorPesquisado;
    }

    public void setMoradorPesquisado(Morador moradorPesquisado) {
        Morador oldPesquisado = this.moradorPesquisado;
        this.moradorPesquisado = moradorPesquisado;
        changeSupport.firePropertyChange("moradorPesquisado", oldPesquisado, moradorPesquisado);
    }
    
    

    public Morador getMoradorSelecionado() {
        return moradorSelecionado;
    }

    public void setMoradorSelecionado(Morador moradorSelecionado) {
        this.moradorSelecionado = moradorSelecionado;
//        if (moradorSelecionado != null) {
//            setMoradorPesquisado(moradorSelecionado);
//        }
    }

    public List<Morador> getMoradoresTabela() {
        return moradoresTabela;
    }

    public void setMoradoresTabela(List<Morador> moradoresTabela) {
        this.moradoresTabela = moradoresTabela;
    }

    public void salvar() {
        moradorDao.salvarAtualizar(moradorDigitado);
        novo();
        pesquisar();
    }

    public void excluir() {
        moradorDao.excluir(moradorDigitado);
        novo();
        pesquisar();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
