/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.controller;

import br.com.smca.rfid.modelo.Bloco;
import br.com.smca.rfid.modelo.repositorio.BlocoDao;
import br.com.smca.rfid.modelo.repositorio.BlocoDaoImpl;
import br.com.smca.rfid.modelo.service.ServiceLocator;
import br.com.smca.rfid.util.ValidacaoException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Gleywson
 */
public final class BlocoControl {
    
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private Bloco blocoDigitado;
    private Bloco blocoSelecionado;
    private List<Bloco> blocosTabela;
    private final BlocoDao blocoDao;

    public BlocoControl() {
        blocoDao = ServiceLocator.getBlocoDao();
        blocosTabela = ObservableCollections.observableList(new ArrayList<>());
        novo();
        pesquisar();
    }

    public void novo() {
        setBlocoDigitado(new Bloco());
    }

    public void pesquisar() {
        blocosTabela.clear();
        blocosTabela.addAll(blocoDao.pesquisar(blocoDigitado));
    }

    public Bloco getBlocoDigitado() {
        return blocoDigitado;
    }

    public void setBlocoDigitado(Bloco blocoDigitado) {
        Bloco oldBlocoDigitado = this.blocoDigitado;
        this.blocoDigitado = blocoDigitado;
        changeSupport.firePropertyChange("blocoDigitado", oldBlocoDigitado, blocoDigitado);
    }

    public Bloco getBlocoSelecionado() {
        return blocoSelecionado;
    }

    public void setBlocoSelecionado(Bloco blocoSelecionado) {
        this.blocoSelecionado = blocoSelecionado;
        if(blocoSelecionado != null) {
            setBlocoDigitado(blocoSelecionado);
        }
    }

    public List<Bloco> getBlocosTabela() {
        return blocosTabela;
    }

    public void setBlocosTabela(List<Bloco> blocosTabela) {
        this.blocosTabela = blocosTabela;
    }

    public void salvar() throws ValidacaoException  {
        blocoDigitado.validar();
        blocoDao.salvarAtualizar(blocoDigitado);
        novo();
        pesquisar();
    }
    
    public void excluir() {
        blocoDao.excluir(blocoDigitado);
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
