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
public class Carro {
    
    private String placa;
    private String cor;
    private String modelo;
    private String marca;
    private int ano;
    private String cpf_dono;

    public Carro() {
    }

    public Carro(String placa, String cor, String modelo, String marca, int ano, String cpf_dono) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cpf_dono = cpf_dono;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCpf_dono() {
        return cpf_dono;
    }

    public void setCpf_dono(String cpf_dono) {
        this.cpf_dono = cpf_dono;
    }
    
    
}
