package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.model.bean.Carona;
import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.dao.Recebe_CaronaDAO;
import br.ufop.brTomaz.util.Operations;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class FXMLHistoricoController implements Initializable {
    @FXML
    ListView<String> listViewCaronas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void obter()
    {
        if(usuarioCorrente.getCnh() == null) {
            obterPassageiro();
        } else {
            obterMotorista();
        }
    }

    @FXML
    private void obterPassageiro()
    {
        List<Carona> caronaList = new ArrayList<>();
        Recebe_CaronaDAO recebeCaronaDAO = new Recebe_CaronaDAO();
        caronaList = recebeCaronaDAO.getCaronasPassageiros(usuarioCorrente.getCpf());
        List<String> list = new ArrayList<>();

        for(Carona carona : caronaList)
        {
            list.add(carona.stringHistorico());
        }

        if (caronaList.size() != 0) {
            listViewCaronas.setItems(FXCollections.observableList(list));
        }
    }

    @FXML
    private void obterMotorista()
    {
        List<Carona> caronaList = new ArrayList<>();
        CaronaDAO caronaDAO = new CaronaDAO();
        caronaList = caronaDAO.caronasMotorista(usuarioCorrente.getCpf());
        List<String> list = new ArrayList<>();

        for(Carona carona : caronaList)
        {
            list.add(carona.stringHistorico());
        }

        if (caronaList.size() != 0) {
            listViewCaronas.setItems(FXCollections.observableList(list));
        }
    }

    @FXML
    private void home() throws IOException {
        Operations.home();
    }

    @FXML
    private void caronas() throws IOException {
        Operations.caronas();
    }

    @FXML
    private void cadastro() throws IOException {
        Operations.cadastro();
    }

    @FXML
    private void historico() throws IOException {
        Operations.historico();
    }

    @FXML
    private void deletar() throws IOException {
        Operations.deletar();
    }


    @FXML
    private void sair() throws IOException {
        Operations.sair();
    }
}
