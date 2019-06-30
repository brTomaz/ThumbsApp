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
 * @author Usuario
 */
public class CaronaDAO {
    private Connection con = null;

    public CaronaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Carona carona) {

        String sql = "INSERT INTO carona(idcarona, origem, destino, dia, horario, valor, quantidade_vagas, quantidade_atual, placa_carro, cpf_motorista) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona.getIdcarona());
            stmt.setString(2, carona.getOrigem());
            stmt.setString(3, carona.getDestino());
            stmt.setString(4, carona.getDia());
            stmt.setString(5, carona.getHorario());
            stmt.setDouble(6, carona.getValor());
            stmt.setInt(7, carona.getQuantidade_vagas());
            stmt.setInt(8, carona.getQuantidade_atual());
            stmt.setString(9, carona.getPlaca_carro());
            stmt.setString(10, carona.getCpf_motorista());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Carona> caronasMotorista(String cpfMotorista) {
        String sql = "SELECT * FROM carona WHERE cpf_motorista = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carona> caronas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpfMotorista);
            rs = stmt.executeQuery();

            caronas = retornaCarona(rs);


        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas;
    }

    public List<Carona> findAll() {

        String sql = "SELECT * FROM carona";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carona> caronas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            caronas = retornaCarona(rs);
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas;
    }

    public boolean delete(Carona carona) {

        String sql = "DELETE FROM carona WHERE idcarona = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona.getIdcarona());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Carona> search(String o, String d) {

        String sql = "SELECT * FROM carona WHERE origem = ? and destino = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carona> caronas = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, o);
            stmt.setString(2, d);

            rs = stmt.executeQuery();
            caronas = retornaCarona(rs);
        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return caronas;
    }

    public Carona getCaronaPassageiro(int id) {

        String sql = "SELECT * FROM carona WHERE idcarona = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Carona carona = new Carona();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            carona = retornaCarona(rs).get(0);

        } catch (SQLException ex) {
            System.err.println("Erro :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carona;
    }

    private List<Carona> retornaCarona(ResultSet rs) throws SQLException {
        List<Carona> caronas = new ArrayList<>();
        while (rs.next()) {

            Carona carona = new Carona();

            carona.setIdcarona(rs.getInt("idcarona"));
            carona.setOrigem(rs.getString("origem"));
            carona.setDestino(rs.getString("destino"));
            carona.setDia(rs.getString("dia"));
            carona.setHorario(rs.getString("horario"));
            carona.setValor(rs.getDouble("valor"));
            carona.setQuantidade_vagas(rs.getInt("quantidade_vagas"));
            carona.setQuantidade_atual(rs.getInt("quantidade_atual"));
            carona.setPlaca_carro(rs.getString("placa_carro"));
            carona.setCpf_motorista(rs.getString("cpf_motorista"));
            caronas.add(carona);
        }
        return caronas;
    }

    public void modificarQuantidadeVagasAtual(Carona carona) {
        String sql = "UPDATE carona SET quantidade_atual = ? WHERE idcarona = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, carona.getQuantidade_atual() + 1);
            stmt.setInt(2, carona.getIdcarona());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
