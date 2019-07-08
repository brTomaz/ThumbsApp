/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.bean;

import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.model.interfaces.Deletavel;
import br.ufop.brTomaz.security.SegurancaSistema;

/**
 *
 * @author Usuario
 */
public class Usuario implements Deletavel {
    
    private String cpf;
    private String nome;
    private String email;
    private String cnh;
    private String senha;
    private String telefone;
    private String nomeDeUsuario;

    public Usuario(){
    }

    public Usuario(String cpf, String nome, String email, String cnh, String senha, String telefone, String nomeDeUsuario) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cnh = cnh;
        this.senha = senha;
        this.telefone = telefone;
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getNomeDeUsuario() { return nomeDeUsuario; }
    public void setNomeDeUsuario(String nomeDeUsuario) { this.nomeDeUsuario = nomeDeUsuario; }

    public String toString() {
        return "Nome: " + getNome();
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) { this.senha = SegurancaSistema.criptografarSenha(senha); }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public void deletar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.delete(this);
    }
}
