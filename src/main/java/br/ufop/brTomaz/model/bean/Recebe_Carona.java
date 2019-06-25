/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.bean;

/**
 *
 * @author Usuario
 */
public class Recebe_Carona {
    private int id_car;
    private String cpf_passageiro;

    public Recebe_Carona() {
    }

    public Recebe_Carona(int id_car, String cpf_passageiro) {
        this.id_car = id_car;
        this.cpf_passageiro = cpf_passageiro;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getCpf_passageiro() {
        return cpf_passageiro;
    }

    public void setCpf_passageiro(String cpf_passageiro) {
        this.cpf_passageiro = cpf_passageiro;
    }
    
    
}
