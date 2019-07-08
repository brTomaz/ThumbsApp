/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.connection.ConnectionFactory;
import br.ufop.brTomaz.model.bean.Carona;
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

        String sql = "INSERT INTO recebe_carona (id_carona, cpf_passageiro) VALUES (?, ?)";

        return statementExecute(r_carona, sql);
    }

    private List<Recebe_Carona> caronasPassageiro(String cpfPassageiro)
    {
        String sql = "SELECT * FROM recebe_carona WHERE cpf_passageiro = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Recebe_Carona> caronas_recebidas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpfPassageiro);
            rs = stmt.executeQuery();
            while(rs.next()){

                Recebe_Carona carona_recebida = new Recebe_Carona();

                carona_recebida.setId_carona(rs.getInt("id_carona"));
                carona_recebida.setCpf_passageiro(rs.getString("cpf_passageiro"));
                caronas_recebidas.add(carona_recebida);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas_recebidas;
    }

    public List<Carona> getCaronasPassageiros(String cpfPassageiro)
    {
        List<Recebe_Carona> caronasRecebeCaronas = caronasPassageiro(cpfPassageiro);
        List<Carona> caronas = new ArrayList<>();
        CaronaDAO caronaDAO = new CaronaDAO();


        for(Recebe_Carona recebe_carona : caronasRecebeCaronas)
        {
            caronas.add(caronaDAO.getCaronaPassageiro(recebe_carona.getId_carona()));
        }

        return caronas;
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

                carona_recebida.setId_carona(rs.getInt("id_carona"));
                carona_recebida.setCpf_passageiro(rs.getString("cpf_passageiro"));

                caronas_recebidas.add(carona_recebida);
            }
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas_recebidas;
    }

    public boolean delete(Recebe_Carona carona_recebida){

        String sql = "DELETE FROM recebe_carona WHERE idcarona = ? and cpf_passageiro = ?";

        return statementExecute(carona_recebida, sql);
    }

    private boolean statementExecute(Recebe_Carona carona_recebida, String sql) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona_recebida.getId_carona());
            stmt.setString(2, carona_recebida.getCpf_passageiro());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}