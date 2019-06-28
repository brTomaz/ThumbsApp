package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLHomePassageiroController implements Initializable {
    @FXML JFXButton btnAlterar;

    @FXML JFXButton btnPesquisar;

    @FXML
    private void alterar() throws IOException {
        MainApplication.setScreen(Screen.ALTERAR_DADOS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
