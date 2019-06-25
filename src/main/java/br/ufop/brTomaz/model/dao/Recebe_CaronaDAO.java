/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.connection.ConnectionFactory;
import br.ufop.brTomaz.model.bean.Recebe_Carona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Recebe_CaronaDAO {
        private Connection con = null;

    public Recebe_CaronaDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Recebe_Carona r_carona){
        
        String sql = "INSERT INTO recebe_carona (id_car, pcpf) VALUES (?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, r_carona.getId_car());
            stmt.setString(2, r_carona.getCpf_passageiro());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Recebe_Carona> findAll(){
        
        String sql = "SELECT * FROM recebe_carona";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Recebe_Carona> caronas_recebidas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Recebe_Carona carona_recebida = new Recebe_Carona();
                
                carona_recebida.setId_car(rs.getInt("id_car"));
                carona_recebida.setCpf_passageiro(rs.getString("pcpf"));
                
                
                caronas_recebidas.add(carona_recebida);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas_recebidas; 
    }
 
    public boolean delete(Recebe_Carona carona_recebida){
        
        String sql = "DELETE FROM recebe_carona WHERE idcar = ? and pcpf = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona_recebida.getId_car());
            stmt.setString(2, carona_recebida.getCpf_passageiro());
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
