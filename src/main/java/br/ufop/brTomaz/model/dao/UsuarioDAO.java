/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufop.brTomaz.model.bean.Recebe_Carona;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.security.SegurancaSistema;

/**
 *
 * @author Usuario
 */
public class UsuarioDAO {

    private Connection con = null;

    public UsuarioDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Usuario usuario){

        String sql = "INSERT INTO usuario(cpf, nome, email, cnh, senha, telefone, nome_usuario) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCnh());
            stmt.setString(5, SegurancaSistema.criptografarSenha(usuario.getSenha()));
            stmt.setString(6, usuario.getTelefone());
            stmt.setString(7, usuario.getNomeDeUsuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Usuario retrieveUser(String e_mail) {
        String sql = "SELECT * FROM usuario AS U WHERE U.email = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, e_mail);
            rs = stmt.executeQuery();
            return getUsuario(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        if(rs.next()){
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cnh = rs.getString("cnh");
            String telefone = rs.getString("telefone");
            String senha = rs.getString("senha");
            String nome_usuario = rs.getString("nome_usuario");

            Usuario usuario = new Usuario(cpf, nome, email, cnh, senha, telefone, nome_usuario);
            return usuario;
        }
        else{
            return null;
        }
    }

    public List<Usuario> findAll(){

        String sql = "SELECT * FROM usuario";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){

                Usuario usuario = new Usuario();

                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCnh(rs.getString("cnh"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNomeDeUsuario(rs.getString("nome_usuario"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }

    public boolean update(Usuario usuario){

        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ?, nome_usuario = ? WHERE cpf = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getNomeDeUsuario());
            stmt.setString(6, usuario.getCpf());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Usuario usuario){

        String sql = "DELETE FROM usuario WHERE cpf = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getCpf());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Usuario buscaMotorista(Recebe_Carona recebe_carona){
        String sql = "SELECT u.* FROM usuario as u, carona as c, recebe_carona as r WHERE c.idcarona = ? and u.cpf = c.cpf_motorista";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, recebe_carona.getId_carona());
            rs = stmt.executeQuery();
            return getUsuario(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
}
