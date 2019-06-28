package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import com.jfoenix.controls.JFXButton;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
