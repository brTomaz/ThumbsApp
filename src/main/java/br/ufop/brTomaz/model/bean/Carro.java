/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.bean;

import br.ufop.brTomaz.model.dao.CarroDAO;
import br.ufop.brTomaz.model.interfaces.Deletavel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Usuario
 */
public class Carro implements Deletavel {
    
    private StringProperty placa = new SimpleStringProperty();
    private StringProperty cor = new SimpleStringProperty();
    private StringProperty modelo = new SimpleStringProperty();
    private StringProperty marca = new SimpleStringProperty();
    private IntegerProperty ano = new SimpleIntegerProperty();
    private StringProperty cpf_dono = new SimpleStringProperty();

    public Carro() {
    }

    public Carro(String placa, String cor, String modelo, String marca, int ano, String cpf_dono) {
        setPlaca(placa);
        setCor(cor);
        setModelo(modelo);
        setMarca(marca);
        setAno(ano);
        setCpf_dono(cpf_dono);
    }

    @Override
    public String toString()
    {
        return "Modelo: " + getModelo() + " | Placa: " + getPlaca();
    }

    public String getPlaca() {
        return placa.get();
    }

    public StringProperty placaProperty() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }

    public String getCor() {
        return cor.get();
    }

    public StringProperty corProperty() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor.set(cor);
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getMarca() {
        return marca.get();
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public int getAno() {
        return ano.get();
    }

    public IntegerProperty anoProperty() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano.set(ano);
    }

    public String getCpf_dono() {
        return cpf_dono.get();
    }

    public StringProperty cpf_donoProperty() {
        return cpf_dono;
    }

    public void setCpf_dono(String cpf_dono) {
        this.cpf_dono.set(cpf_dono);
    }

    @Override
    public void deletar() {
        CarroDAO carroDAO = new CarroDAO();
        carroDAO.delete(this);
    }
}
