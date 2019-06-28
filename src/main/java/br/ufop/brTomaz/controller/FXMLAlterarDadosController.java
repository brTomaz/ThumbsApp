package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.util.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class FXMLAlterarDadosController implements Initializable {

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtNomeUsuario;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnAlterar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaskFieldUtil.foneField(txtTelefone);

        BooleanProperty sizeFone = new SimpleBooleanProperty(true);

        txtTelefone.textProperty().addListener((ob, ov, nv) -> {
            sizeFone.setValue(nv.length() < 14);
        });

        btnAlterar.disableProperty().bind(
                txtNome.textProperty().isEmpty()
                                .or(txtNome.textProperty().isEmpty()
                                        .or(txtSenha.textProperty().isEmpty()
                                                .or(txtEmail.textProperty().isEmpty()
                                                        .or(txtTelefone.textProperty().isEmpty())
                                                        .or(sizeFone)
                                                )
                                        )
                                )
        );
    }

    @FXML
    private void alterar() throws IOException {
        String nome = txtNome.getText();
        String nomeUsuario = txtNomeUsuario.getText();
        String telefone = MaskFieldUtil.onlyDigitsValue(txtTelefone);
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        Usuario usuario = new Usuario(usuarioCorrente.getCpf(), nome, email, null, senha, telefone, nomeUsuario);

        Boolean ok = new UsuarioDAO().update(usuario);

        if (ok) {
            if(usuarioCorrente.getCnh() != null)
            {
                MainApplication.setScreen(Screen.HOME_MOTORISTA);
            }
            else
            {
                MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }
}