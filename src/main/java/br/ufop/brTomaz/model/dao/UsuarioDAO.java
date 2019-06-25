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
import br.ufop.brTomaz.model.bean.Usuario;

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
        
        String sql = "INSERT INTO usuario(cpf, nome, email, cnh, senha, telefone, nick) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCnh());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getTelefone());
            stmt.setString(7, usuario.getNomeDeUsuario());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Usuario retrieveUser(String email)
    {
        //TODO
         return null;

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
                usuario.setNomeDeUsuario(rs.getString("nick"));
                
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
        
        String sql = "UPDATE usuario SET nome = ?, endereco = ?, data_nasc = ?, email = ?, cnh = ?, senha = ?, telefone = ?, nick = ? WHERE cpf = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCnh());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getNomeDeUsuario());
            stmt.setString(7, usuario.getCpf());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
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
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
}
