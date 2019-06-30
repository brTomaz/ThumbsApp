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
    private int id_carona;
    private int id_caronaPassageiro;
    private String cpf_passageiro;

    public Recebe_Carona() {
    }

    public Recebe_Carona(int id_carona, int id_caronaPassageiro, String cpf_passageiro) {
        this.id_carona = id_carona;
        this.id_caronaPassageiro = id_caronaPassageiro;
        this.cpf_passageiro = cpf_passageiro;
    }

    public int getId_carona() {
        return id_carona;
    }

    public void setId_carona(int id_carona) {
        this.id_carona = id_carona;
    }

    public int getId_caronaPassageiro() {
        return id_caronaPassageiro;
    }

    public void setId_caronaPassageiro(int id_caronaPassageiro) {
        this.id_caronaPassageiro = id_caronaPassageiro;
    }

    public String getCpf_passageiro() {
        return cpf_passageiro;
    }

    public void setCpf_passageiro(String cpf_passageiro) {
        this.cpf_passageiro = cpf_passageiro;
    }
}
