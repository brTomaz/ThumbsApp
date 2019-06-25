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
import br.ufop.brTomaz.model.bean.Carona;

/**
 *
 * @author Usuario
 */
public class CaronaDAO {
    private Connection con = null;

    public CaronaDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Carona carona){
        
        String sql = "INSERT INTO carona(idcarona, origem, destino, dia, horario, valor, vagas, mcpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona.getIdcarona());
            stmt.setString(2, carona.getOrigem());
            stmt.setString(3, carona.getDestino());
            stmt.setString(4, carona.getDia());
            stmt.setString(5, carona.getHora());
            stmt.setDouble(6, carona.getValor());
            stmt.setInt(7, carona.getVagas());
            stmt.setString(8, carona.getCpf_motorista());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Carona> findAll(){
        
        String sql = "SELECT * FROM carona";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Carona> caronas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Carona carona = new Carona();
                
                carona.setIdcarona(rs.getInt("idcarona"));
                carona.setOrigem(rs.getString("origem"));
                carona.setDestino(rs.getString("destino"));
                carona.setDia(rs.getString("dia"));
                carona.setHora(rs.getString("horario"));
                carona.setValor(rs.getDouble("valor"));
                carona.setVagas(rs.getInt("vagas"));
                carona.setCpf_motorista(rs.getString("mcpf"));
                
                caronas.add(carona);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas; 
    }
    
    public boolean delete(Carona carona){
        
        String sql = "DELETE FROM carona WHERE idcarona = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona.getIdcarona());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Carona> search(String o, String d){
        
        String sql = "SELECT * FROM carona WHERE origem = ? and destino = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Carona> caronas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, o);
            stmt.setString(2, d);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Carona carona = new Carona();
                
                carona.setIdcarona(rs.getInt("idcarona"));
                carona.setOrigem(rs.getString("origem"));
                carona.setDestino(rs.getString("destino"));
                carona.setDia(rs.getString("dia"));
                carona.setHora(rs.getString("horario"));
                carona.setValor(rs.getDouble("valor"));
                carona.setVagas(rs.getInt("vagas"));
                carona.setCpf_motorista(rs.getString("mcpf"));
                
                caronas.add(carona);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas; 
    }
}
