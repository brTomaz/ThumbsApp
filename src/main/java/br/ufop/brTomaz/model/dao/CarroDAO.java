/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.connection.ConnectionFactory;
import br.ufop.brTomaz.model.bean.Carro;

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
public class CarroDAO {
        
    private Connection con = null;

    public CarroDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Carro carro){
        
        String sql = "INSERT INTO carro (placa, cor, modelo, marca, ano, cpf_dono) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getCor());
            stmt.setString(3, carro.getModelo());
            stmt.setString(4, carro.getMarca());
            stmt.setInt(5, carro.getAno());
            stmt.setString(6, carro.getCpf_dono());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Carro> findAll(){
        
        String sql = "SELECT * FROM carro";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Carro> carros = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Carro carro = new Carro();
                
                carro.setPlaca(rs.getString("placa"));
                carro.setCor(rs.getString("cor"));
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setAno(rs.getInt("ano"));
                carro.setCpf_dono(rs.getString("cpf_dono"));
                
                carros.add(carro);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carros; 
    }
    
        public List<Carro> search(String cpf){
        
        String sql = "SELECT * FROM carro WHERE cpf_dono = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Carro> carros = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Carro carro = new Carro();
                
                carro.setPlaca(rs.getString("placa"));
                carro.setCor(rs.getString("cor"));
                carro.setModelo(rs.getString("modelo"));
                carro.setMarca(rs.getString("marca"));
                carro.setAno(rs.getInt("ano"));
                carro.setCpf_dono(rs.getString("cpf_dono"));
                
                carros.add(carro);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carros; 
    }

    public Carro retrieveCarro(String placa) {
        String sql = "SELECT * FROM carro AS U WHERE U.placa = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, placa);
            rs = stmt.executeQuery();
            if(rs.next()){
                String cor = rs.getString("cor");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                int ano  = rs.getInt("ano");
                String cpf_dono = rs.getString("cpf_dono");

                Carro carro = new Carro(placa, cor, modelo, marca, ano, cpf_dono);
                return carro;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }

    public boolean delete(Carro carro){
        
        String sql = "DELETE FROM carro WHERE placa = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, carro.getPlaca());
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
