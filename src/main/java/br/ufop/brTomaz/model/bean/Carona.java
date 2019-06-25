/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.bean;

import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class Carona {
    private int idcarona;
    private String origem;
    private String destino;
    private String dia;
    private String hora;
    private double valor;
    private int vagas;
    private String cpf_motorista;

    public Carona() {
    }

    public Carona(int idcarona, String origem, String destino, String dia, String hora, double valor, int vagas, String cpf_motorista) {
        this.idcarona = idcarona;
        this.origem = origem;
        this.destino = destino;
        this.dia = dia;
        this.hora = hora;
        this.valor = valor;
        this.vagas = vagas;
        this.cpf_motorista = cpf_motorista;
    }

    public int getIdcarona() {
        return idcarona;
    }

    public void setIdcarona(int idcarona) {
        this.idcarona = idcarona;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(String cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }
    
    
}
