package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.util.Operations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class FXMLHomeMotoristaController implements Initializable {
    @FXML
    private void alterar() throws IOException {
        MainApplication.setScreen(Screen.ALTERAR_DADOS);
    }

    @FXML
    private void adicionarCarro() throws IOException {
        MainApplication.setScreen(Screen.CADASTRO_CARRO);
    }

    @FXML
    private void oferecerCarona() throws IOException {
        MainApplication.setScreen(Screen.OFERECER_CARONA);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void pesquisar() throws IOException {
        Operations.caronas();
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
    private void deletar() throws IOException, NoSuchAlgorithmException {
        Operations.deletar();
    }

    @FXML
    private void sair() throws IOException {
        Operations.sair();
    }
}
