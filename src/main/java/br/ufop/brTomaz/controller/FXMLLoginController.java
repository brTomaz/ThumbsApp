package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.security.SegurancaSistema;
import br.ufop.brTomaz.util.Operations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class FXMLLoginController implements Initializable {

    @FXML private JFXTextField txtEmail;
    @FXML private JFXPasswordField txtSenha;
    @FXML private JFXButton btnEntrar;

    private BooleanProperty permission = new SimpleBooleanProperty(false);
    private IntegerProperty tries = new SimpleIntegerProperty(3);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        permission.addListener((ob, ov, nv) -> {
            if(nv) {
                try {
                    Screen screen = usuarioCorrente.getCnh() == null ? Screen.HOME_PASSAGEIRO : Screen.HOME_MOTORISTA;
                    MainApplication.setScreen(screen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tries.addListener((ob, ov, nv) -> {
            if(nv.intValue() > 0 && !permission.getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Senha inválida");
                alert.setContentText("Tentativas restantes: " + nv.intValue());
                alert.show();
                txtSenha.clear();
            }
            else if(!permission.getValue())
                MainApplication.closeApplication();
        });

        btnEntrar.disableProperty()
                .bind(txtEmail.textProperty().isEmpty()
                        .or(txtSenha.textProperty().isEmpty())
                );
    }

    @FXML
    private void entrar() throws IOException {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioCorrente = usuarioDAO.retrieveUser(email);

        if(usuarioCorrente != null)
        {
            permission.setValue(usuarioCorrente.getSenha().equals(SegurancaSistema.criptografarSenha(senha)));
            tries.set(tries.getValue() - 1);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Usuário inválido");
            alert.setContentText("O email digitado não está cadastrado no sistema.");
            alert.show();

            txtEmail.clear();
            txtSenha.clear();
        }
    }

    @FXML
    private void cadastrar() throws IOException {
        MainApplication.setScreen(Screen.CADASTRO);
    }

    @FXML
    private void administrador() throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Administrador");
        dialog.setHeaderText("Senha do administrador");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        String password = Operations.panePassword(dialog, loginButtonType);

        if(password.equals("admin"))
        {
            MainApplication.setScreen(Screen.ADMINISTRADOR);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Senha inválida");
            alert.setContentText("A senha digitada está incorreta.");
            alert.show();
        }
    }
}
