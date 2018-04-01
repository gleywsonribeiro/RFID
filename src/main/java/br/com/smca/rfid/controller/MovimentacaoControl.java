/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smca.rfid.controller;

import br.com.smca.rfid.modelo.Morador;
import br.com.smca.rfid.modelo.Movimentacao;
import br.com.smca.rfid.modelo.TipoMovimentacao;
import br.com.smca.rfid.modelo.repositorio.MoradorDao;
import br.com.smca.rfid.modelo.repositorio.MovimentacaoDao;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Gleywson
 */
public class MovimentacaoControl {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    private List<Morador> moradores; //contem uma lista de moradores
    private Morador moradorSelecionado; //representa o morador seleciobado
    private Morador moradorDigitado;
    
    private MovimentacaoDao movimentacaoDao;
    private MoradorDao moradorDao;

    public MovimentacaoControl() {
        movimentacaoDao = new MovimentacaoDao();
        moradorDao = new MoradorDao();
        moradores = ObservableCollections.observableList(new ArrayList<>());
    }
    
    public void pesquisar() {
        moradores.clear();
        moradores.addAll(moradorDao.pesquisar(moradorSelecionado));
    }

    public Morador getMoradorSelecionado() {
        return moradorSelecionado;
    }

    public void setMoradorSelecionado(Morador moradorSelecionado) {
        Morador oldMoradorSelecionado = this.moradorSelecionado;
        this.moradorSelecionado = moradorSelecionado;
        changeSupport.firePropertyChange("Morador Selecionado", oldMoradorSelecionado, moradorSelecionado);
    }

    public Morador getMoradorDigitado() {
        return moradorDigitado;
    }

    public void setMoradorDigitado(Morador moradorDigitado) {
        Morador oldMoradorDigitado = this.moradorDigitado;
        this.moradorDigitado = moradorDigitado;
        changeSupport.firePropertyChange("Morador Digitado", oldMoradorDigitado, moradorDigitado);
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }
    
    public void registrarEntrada() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setCondomino(moradorSelecionado);
        movimentacao.setDataMovimentacao(new Date());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.E);
        
        movimentacaoDao.salvar(movimentacao);
        JOptionPane.showConfirmDialog(null, "Registro realizado");
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
