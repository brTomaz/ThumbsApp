package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLLoginController implements Initializable {

    @FXML private JFXTextField txtEmail;
    @FXML private JFXPasswordField txtSenha;
    @FXML private JFXButton btnEntrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEntrar.disableProperty()
                .bind(txtEmail.textProperty().isEmpty()
                        .or(txtSenha.textProperty().isEmpty())
                );
    }

    @FXML
    private void entrar(){
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
    }

    @FXML
    private void cadastrar() throws IOException {
        MainApplication.setScreen(Screen.CADASTRO);
    }
}
