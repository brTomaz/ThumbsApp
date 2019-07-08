/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.bean;

import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.interfaces.Deletavel;

/**
 *
 * @author Usuario
 */
public class Carona implements Deletavel {
    private int idcarona;
    private String origem;
    private String destino;
    private String dia;
    private String horario;
    private double valor;
    private int quantidade_vagas;
    private int quantidade_atual;
    private String placa_carro;
    private String cpf_motorista;

    public Carona(int idcarona, String origem, String destino, String dia, String horario, double valor, int quantidade_vagas, int quantidade_atual, String placa_carro, String cpf_motorista) {
        this.idcarona = idcarona;
        this.origem = origem;
        this.destino = destino;
        this.dia = dia;
        this.horario = horario;
        this.valor = valor;
        this.quantidade_vagas = quantidade_vagas;
        this.quantidade_atual = quantidade_atual;
        this.placa_carro = placa_carro;
        this.cpf_motorista = cpf_motorista;
    }

    public Carona() {

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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade_vagas() {
        return quantidade_vagas;
    }

    public void setQuantidade_vagas(int quantidade_vagas) {
        this.quantidade_vagas = quantidade_vagas;
    }

    public int getQuantidade_atual() {
        return quantidade_atual;
    }

    public void setQuantidade_atual(int quantidade_atual) {
        this.quantidade_atual = quantidade_atual;
    }

    public String getPlaca_carro() {
        return placa_carro;
    }

    public void setPlaca_carro(String placa_carro) {
        this.placa_carro = placa_carro;
    }

    public String getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(String cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    @Override
    public String toString()
    {
        String[] splitDia = dia.split("-");
        String dia = splitDia[2];
        String mes = splitDia[1];
        String ano = splitDia[0];
        return "Dia: " + dia + "/" + mes + "/" + ano + "   ---   Horário: " + getHorario() + "   ---   Carro: " + getPlaca_carro() + "   ---   Vagas: " + (getQuantidade_vagas() - getQuantidade_atual());
    }

    public String stringHistorico()
    {
        String[] splitDia = dia.split("-");
        String dia = splitDia[2];
        String mes = splitDia[1];
        String ano = splitDia[0];
        return "Dia: " + dia + "/" + mes + "/" + ano + "   ---   Horário: " + getHorario() + "   ---   Carro: " + getPlaca_carro();
    }

    @Override
    public void deletar() {
        CaronaDAO caronaDAO = new CaronaDAO();
        caronaDAO.delete(this);
    }
}
